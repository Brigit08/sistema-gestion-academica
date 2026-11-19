package pe.udh.gestionacademica.exception;

public class PrerrequisitoNoCumplidoException extends GestionAcademicaException {

    private final int idCurso;
    private final int idCursoRequisito;

    public PrerrequisitoNoCumplidoException(int idCurso, int idCursoRequisito) {
        super("No se cumple el prerrequisito: el curso " + idCurso
                + " requiere aprobar el curso " + idCursoRequisito);
        this.idCurso = idCurso;
        this.idCursoRequisito = idCursoRequisito;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public int getIdCursoRequisito() {
        return idCursoRequisito;
    }
}
