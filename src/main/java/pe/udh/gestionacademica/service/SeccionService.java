package pe.udh.gestionacademica.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import pe.udh.gestionacademica.exception.DatosInvalidosException;
import pe.udh.gestionacademica.exception.RecursoNoEncontradoException;
import pe.udh.gestionacademica.model.DiaSemana;
import pe.udh.gestionacademica.model.HorarioSeccion;
import pe.udh.gestionacademica.model.Seccion;
import pe.udh.gestionacademica.util.GeneradorId;
import pe.udh.gestionacademica.util.Validaciones;

public class SeccionService {

    private final CursoService cursos;
    private final Map<Integer, Seccion> secciones = new ConcurrentHashMap<>();
    private final GeneradorId idsHorario;

    public SeccionService(CursoService cursos) {
        this(cursos, new GeneradorId(1));
    }

    public SeccionService(CursoService cursos, GeneradorId idsHorario) {
        Validaciones.noNulo(cursos, "cursos");
        Validaciones.noNulo(idsHorario, "idsHorario");
        this.cursos = cursos;
        this.idsHorario = idsHorario;
    }

    public void registrar(Seccion seccion) {
        Validaciones.noNulo(seccion, "seccion");
        Validaciones.enteroPositivo(seccion.getIdSeccion(), "idSeccion");
        cursos.buscar(seccion.getIdCurso());
        if (secciones.putIfAbsent(seccion.getIdSeccion(), seccion) != null) {
            throw new DatosInvalidosException("Ya existe una seccion con id "
                    + seccion.getIdSeccion());
        }
    }

    public Seccion buscar(int idSeccion) {
        Seccion seccion = secciones.get(idSeccion);
        if (seccion == null) {
            throw new RecursoNoEncontradoException("No existe la seccion " + idSeccion);
        }
        return seccion;
    }

    public List<Seccion> listarPorPeriodo(int idPeriodo) {
        return secciones.values().stream()
                .filter(seccion -> seccion.getIdPeriodo() == idPeriodo)
                .sorted((a, b) -> Integer.compare(a.getIdSeccion(), b.getIdSeccion()))
                .toList();
    }

    public HorarioSeccion agregarHorario(int idSeccion, int idAula, DiaSemana diaSemana,
                                         LocalTime horaInicio, LocalTime horaFin) {
        Seccion seccion = buscar(idSeccion);
        HorarioSeccion horario = new HorarioSeccion(idsHorario.siguiente(), idSeccion,
                idAula, diaSemana, horaInicio, horaFin);
        for (HorarioSeccion existente : seccion.getHorarios()) {
            if (horario.seSolapaCon(existente)) {
                throw new DatosInvalidosException("El horario se superpone con otro horario de la misma seccion");
            }
        }
        seccion.agregarHorario(horario);
        return horario;
    }
}
