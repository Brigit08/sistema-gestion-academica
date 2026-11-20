import clases.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatriculaControlador {

    private List<Matricula> matriculas;
    private List<DetalleMatricula> detalles;

    private List<Seccion> secciones;
    private List<Horario> horarios;
    private List<Curso> cursos;
    private List<Prerrequisito> prerrequisitos;
    private List<Nota> notas;

    private ValidadorPrerrequisitos validadorPrerrequisitos;
    private ValidadorCruceHorario validadorCruceHorario;
    private ControlCupos controlCupos;

    public MatriculaControlador(
            List<Matricula> matriculas,
            List<DetalleMatricula> detalles,
            List<Seccion> secciones,
            List<Horario> horarios,
            List<Curso> cursos,
            List<Prerrequisito> prerrequisitos,
            List<Nota> notas) {

        this.matriculas = matriculas;
        this.detalles = detalles;
        this.secciones = secciones;
        this.horarios = horarios;
        this.cursos = cursos;
        this.prerrequisitos = prerrequisitos;
        this.notas = notas;

        this.validadorPrerrequisitos =
            new ValidadorPrerrequisitos();

        this.validadorCruceHorario =
            new ValidadorCruceHorario();

        this.controlCupos = new ControlCupos();
    }

    public String matricular(String codigoEstudiante,
                             int idSeccion) {

        Seccion seccionSeleccionada = null;

        // Buscar la sección seleccionada
        for (Seccion seccion : secciones) {

            if (seccion.getIdSeccion() == idSeccion) {
                seccionSeleccionada = seccion;
                break;
            }
        }

        if (seccionSeleccionada == null) {
            return "La sección no existe.";
        }

        // Buscar el curso de la sección
        Curso cursoSeleccionado = null;

        for (Curso curso : cursos) {

            if (curso.getIdCurso() ==
                seccionSeleccionada.getIdCurso()) {

                cursoSeleccionado = curso;
                break;
            }
        }

        if (cursoSeleccionado == null) {
            return "No se encontró el curso.";
        }

        // Buscar matrícula existente del estudiante
        // en el mismo periodo académico
        Matricula matriculaActual = null;

        for (Matricula matricula : matriculas) {

            if (matricula.getCodigoEstdiante().equals(codigoEstudiante)
                && matricula.getIdPeriodo() ==
                seccionSeleccionada.getIdPeriodo()
                && matricula.getEstado().equalsIgnoreCase("ACTIVA")) {

                matriculaActual = matricula;
                break;
            }
        }

        // Verificar que no esté inscrito anteriormente
        if (matriculaActual != null) {

            for (DetalleMatricula detalle : detalles) {

                if (detalle.getIdMatricula() ==
                    matriculaActual.getIdMatricula()
                    && detalle.getIdSeccion() == idSeccion
                    && detalle.getEstado().equalsIgnoreCase("ACTIVO")) {

                    return "El estudiante ya está inscrito en esta sección.";
                }
            }
        }

        // Obtener las secciones en las que ya está inscrito
        List<Integer> seccionesInscritas = new ArrayList<>();

        if (matriculaActual != null) {

            for (DetalleMatricula detalle : detalles) {

                if (detalle.getIdMatricula() ==
                    matriculaActual.getIdMatricula()
                    && detalle.getEstado().equalsIgnoreCase("ACTIVO")) {

                    seccionesInscritas.add(detalle.getIdSeccion());
                }
            }
        }

        // Validar prerrequisitos
        if (!validadorPrerrequisitos.validar(
                cursoSeleccionado.getIdCurso(),
                prerrequisitos,
                notas,
                detalles,
                matriculas,
                secciones,
                codigoEstudiante)) {

            return "No cumple con los prerrequisitos del curso.";
        }

        // Validar cruces de horario
        if (!validadorCruceHorario.validar(
                idSeccion,
                seccionesInscritas,
                horarios)) {

            return "Existe un cruce de horario.";
        }

        // Validar y reservar cupo
        if (!controlCupos.reservarCupo(seccionSeleccionada)) {

            return "No hay cupos disponibles.";
        }

        // Crear matrícula si todavía no existe
        if (matriculaActual == null) {

            int nuevoId = matriculas.size() + 1;

            matriculaActual = new Matricula(
                nuevoId,
                codigoEstudiante,
                seccionSeleccionada.getIdPeriodo(),
                new Date(),
                "ACTIVA",
                0
            );

            matriculas.add(matriculaActual);
        }

        // Registrar detalle de matrícula
        int nuevoIdDetalle = detalles.size() + 1;

        DetalleMatricula nuevoDetalle = new DetalleMatricula(
            nuevoIdDetalle,
            matriculaActual.getIdMatricula(),
            idSeccion,
            "ACTIVO"
        );

        detalles.add(nuevoDetalle);

        // Actualizar total de créditos
        int nuevosCreditos =
            matriculaActual.getTotalCreditos()
            + cursoSeleccionado.getCreditos();

        matriculaActual.setTotalCreditos(nuevosCreditos);

        return "Matrícula realizada correctamente.";
    }

    public List<Matricula> listarMatriculas() {
        return matriculas;
    }

    public List<DetalleMatricula> listarDetalles() {
        return detalles;
    }
}
