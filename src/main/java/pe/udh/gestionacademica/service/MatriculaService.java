package pe.udh.gestionacademica.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import pe.udh.gestionacademica.exception.DatosInvalidosException;
import pe.udh.gestionacademica.exception.MatriculaDuplicadaException;
import pe.udh.gestionacademica.exception.RecursoNoEncontradoException;
import pe.udh.gestionacademica.model.DetalleMatricula;
import pe.udh.gestionacademica.model.Estudiante;
import pe.udh.gestionacademica.model.Matricula;
import pe.udh.gestionacademica.model.Seccion;
import pe.udh.gestionacademica.util.GeneradorId;
import pe.udh.gestionacademica.util.Validaciones;

public class MatriculaService {

    private final EstudianteService estudiantes;
    private final CursoService cursos;
    private final SeccionService secciones;
    private final PeriodoAcademicoService periodos;
    private final CatalogoService catalogo;
    private final ControlCupos controlCupos;
    private final ValidadorPrerrequisitos validadorPrerrequisitos;
    private final ValidadorCruceHorario validadorCruceHorario;
    private final MotorReglasAcademicas motorReglas;
    private final Map<Integer, Matricula> matriculas;
    private final Map<String, Object> bloqueos;
    private final GeneradorId idsMatricula;
    private final GeneradorId idsDetalle;

    public MatriculaService(EstudianteService estudiantes, CursoService cursos,
                            SeccionService secciones, PeriodoAcademicoService periodos,
                            CatalogoService catalogo, ControlCupos controlCupos,
                            ValidadorPrerrequisitos validadorPrerrequisitos,
                            ValidadorCruceHorario validadorCruceHorario,
                            MotorReglasAcademicas motorReglas) {
        this.estudiantes = estudiantes;
        this.cursos = cursos;
        this.secciones = secciones;
        this.periodos = periodos;
        this.catalogo = catalogo;
        this.controlCupos = controlCupos;
        this.validadorPrerrequisitos = validadorPrerrequisitos;
        this.validadorCruceHorario = validadorCruceHorario;
        this.motorReglas = motorReglas;
        this.matriculas = new ConcurrentHashMap<>();
        this.bloqueos = new ConcurrentHashMap<>();
        this.idsMatricula = new GeneradorId(1);
        this.idsDetalle = new GeneradorId(1);
    }

    public Matricula matricular(String codigoEstudiante, int idPeriodo, List<Integer> idsSecciones) {
        String codigo = Validaciones.requerido(codigoEstudiante, "codigoEstudiante");
        Validaciones.noNulo(idsSecciones, "secciones");
        if (idsSecciones.isEmpty()) {
            throw new DatosInvalidosException("Debe seleccionar al menos una seccion");
        }
        String clave = codigo + "#" + idPeriodo;
        Object bloqueo = bloqueos.computeIfAbsent(clave, k -> new Object());
        synchronized (bloqueo) {
            return matricularInterno(codigo, idPeriodo, idsSecciones);
        }
    }

    public Matricula anular(int idMatricula) {
        Matricula matricula = buscar(idMatricula);
        if (!matricula.estaRegistrada()) {
            throw new DatosInvalidosException("La matricula " + idMatricula + " no esta registrada");
        }
        matricula.getDetalles().stream()
                .filter(DetalleMatricula::estaActivo)
                .forEach(detalle -> controlCupos.liberar(secciones.buscar(detalle.getIdSeccion())));
        matricula.anular();
        return matricula;
    }

    public Matricula buscar(int idMatricula) {
        Matricula matricula = matriculas.get(idMatricula);
        if (matricula == null) {
            throw new RecursoNoEncontradoException("No existe la matricula " + idMatricula);
        }
        return matricula;
    }

    public DetalleMatricula buscarDetalle(int idDetalle) {
        return matriculas.values().stream()
                .flatMap(matricula -> matricula.getDetalles().stream())
                .filter(detalle -> detalle.getIdDetalle() == idDetalle)
                .findFirst()
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el detalle de matricula " + idDetalle));
    }

    public List<Matricula> listar() {
        return matriculas.values().stream()
                .sorted(Comparator.comparingInt(Matricula::getIdMatricula))
                .toList();
    }

    public List<Matricula> listarPorEstudiante(String codigoEstudiante) {
        String codigo = Validaciones.requerido(codigoEstudiante, "codigoEstudiante");
        return matriculas.values().stream()
                .filter(matricula -> matricula.getCodigoEstudiante().equals(codigo))
                .sorted(Comparator.comparingInt(Matricula::getIdMatricula))
                .toList();
    }

    public List<Matricula> listarPorPeriodo(int idPeriodo) {
        return matriculas.values().stream()
                .filter(matricula -> matricula.getIdPeriodo() == idPeriodo)
                .sorted(Comparator.comparingInt(Matricula::getIdMatricula))
                .toList();
    }

    private Matricula matricularInterno(String codigo, int idPeriodo, List<Integer> idsSecciones) {
        Estudiante estudiante = estudiantes.buscar(codigo);
        periodos.validarMatriculaAbierta(idPeriodo, LocalDate.now());
        validarNoDuplicada(codigo, idPeriodo);
        List<Seccion> seccionesSeleccionadas = resolverSecciones(idPeriodo, idsSecciones);
        int totalCreditos = calcularCreditos(seccionesSeleccionadas);
        int idFacultad = catalogo.obtenerIdFacultadDeCarrera(estudiante.getIdCarrera());
        motorReglas.validarMatricula(idFacultad, totalCreditos, seccionesSeleccionadas.size());
        validarPrerrequisitos(estudiante, seccionesSeleccionadas);
        validadorCruceHorario.validar(seccionesSeleccionadas, Collections.emptyList());
        reservarCupos(seccionesSeleccionadas);
        return crearMatricula(codigo, idPeriodo, totalCreditos, seccionesSeleccionadas);
    }

    private void validarNoDuplicada(String codigo, int idPeriodo) {
        boolean existe = matriculas.values().stream()
                .anyMatch(matricula -> matricula.estaRegistrada()
                        && matricula.getIdPeriodo() == idPeriodo
                        && matricula.getCodigoEstudiante().equals(codigo));
        if (existe) {
            throw new MatriculaDuplicadaException("El estudiante " + codigo
                    + " ya tiene una matricula registrada en el periodo " + idPeriodo);
        }
    }

    private List<Seccion> resolverSecciones(int idPeriodo, List<Integer> idsSecciones) {
        LinkedHashSet<Integer> idsUnicos = new LinkedHashSet<>(idsSecciones);
        if (idsUnicos.size() != idsSecciones.size()) {
            throw new DatosInvalidosException("No se puede repetir una seccion en la matricula");
        }
        List<Seccion> resultado = new ArrayList<>();
        for (Integer idSeccion : idsUnicos) {
            Validaciones.noNulo(idSeccion, "idSeccion");
            Validaciones.enteroPositivo(idSeccion, "idSeccion");
            Seccion seccion = secciones.buscar(idSeccion);
            if (seccion.getIdPeriodo() != idPeriodo) {
                throw new DatosInvalidosException("La seccion " + idSeccion
                        + " no pertenece al periodo " + idPeriodo);
            }
            resultado.add(seccion);
        }
        return resultado;
    }

    private int calcularCreditos(List<Seccion> seccionesSeleccionadas) {
        int total = 0;
        for (Seccion seccion : seccionesSeleccionadas) {
            total += cursos.buscar(seccion.getIdCurso()).getCreditos();
        }
        return total;
    }

    private void validarPrerrequisitos(Estudiante estudiante, List<Seccion> seccionesSeleccionadas) {
        for (Seccion seccion : seccionesSeleccionadas) {
            validadorPrerrequisitos.validar(estudiante, cursos.buscar(seccion.getIdCurso()));
        }
    }

    private void reservarCupos(List<Seccion> seccionesSeleccionadas) {
        List<Seccion> ordenadas = new ArrayList<>(seccionesSeleccionadas);
        ordenadas.sort(Comparator.comparingInt(Seccion::getIdSeccion));
        List<Seccion> reservadas = new ArrayList<>();
        try {
            for (Seccion seccion : ordenadas) {
                controlCupos.reservar(seccion);
                reservadas.add(seccion);
            }
        } catch (RuntimeException excepcion) {
            reservadas.forEach(controlCupos::liberar);
            throw excepcion;
        }
    }

    private Matricula crearMatricula(String codigo, int idPeriodo, int totalCreditos,
                                     List<Seccion> seccionesSeleccionadas) {
        Matricula matricula = new Matricula(idsMatricula.siguiente(), codigo, idPeriodo,
                LocalDateTime.now(), Matricula.ESTADO_REGISTRADA);
        matricula.setTotalCreditos(totalCreditos);
        for (Seccion seccion : seccionesSeleccionadas) {
            matricula.agregarDetalle(new DetalleMatricula(idsDetalle.siguiente(),
                    matricula.getIdMatricula(), seccion.getIdSeccion(),
                    DetalleMatricula.ESTADO_ACTIVO));
        }
        matriculas.put(matricula.getIdMatricula(), matricula);
        return matricula;
    }
}
