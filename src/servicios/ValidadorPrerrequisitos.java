
import java.util.List;

public class ValidadorPrerrequisitos {

    public boolean validar(int idCurso,
                           List<Prerrequisito> prerrequisitos,
                           List<Nota> notas,
                           List<DetalleMatricula> detalles,
                           List<Matricula> matriculas,
                           List<Seccion> secciones,
                           String codigoEstudiante) {

        for (Prerrequisito requisito : prerrequisitos) {

            if (requisito.getIdCurso() == idCurso) {

                boolean aprobado = false;

                for (Nota nota : notas) {

                    if (nota.getValor() >= 11) {

                        for (DetalleMatricula detalle : detalles) {

                            if (detalle.getIdDetalle() ==
                                nota.getIdDetalle()) {

                                for (Matricula matricula : matriculas) {

                                    if (matricula.getIdMatricula() ==
                                        detalle.getIdMatricula()
                                        && matricula.getCodigoEstudiante()
                                        .equals(codigoEstudiante)) {

                                        for (Seccion seccion : secciones) {

                                            if (seccion.getIdSeccion() ==
                                                detalle.getIdSeccion()
                                                && seccion.getIdCurso() ==
                                                requisito.getIdCursoRequisito()) {

                                                aprobado = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (!aprobado) {
                    return false;
                }
            }
        }

        return true;
    }
}
