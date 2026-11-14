public class Prerrequisito {

    private int idCurso;
    private int idCursoRequisito;

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