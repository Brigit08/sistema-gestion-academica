package pe.udh.gestionacademica.model;

public class Prerrequisito {

    private final int idCurso;
    private final int idCursoRequisito;

    public Prerrequisito(int idCurso, int idCursoRequisito) {
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
