package pe.udh.gestionacademica.model;

public class Prerrequisito {

    private int idCurso;
    private int idCursoRequisito;

    public Prerrequisito() {
    }

    public Prerrequisito(int idCurso, int idCursoRequisito) {
        this.idCurso = idCurso;
        this.idCursoRequisito = idCursoRequisito;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdCursoRequisito() {
        return idCursoRequisito;
    }

    public void setIdCursoRequisito(int idCursoRequisito) {
        this.idCursoRequisito = idCursoRequisito;
    }
}