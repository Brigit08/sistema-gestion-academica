public class Seccion {

    private int idSeccion;
    private int idCurso;
    private int idPeriodo;
    private int idDocente;
    private String codigoSeccion;
    private int cupoMaximo;
    private int cupoOcupado;

    public Seccion(int idSeccion, int idCurso, int idPeriodo,
                   int idDocente, String codigoSeccion,
                   int cupoMaximo, int cupoOcupado) {

        this.idSeccion = idSeccion;
        this.idCurso = idCurso;
        this.idPeriodo = idPeriodo;
        this.idDocente = idDocente;
        this.codigoSeccion = codigoSeccion;
        this.cupoMaximo = cupoMaximo;
        this.cupoOcupado = cupoOcupado;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public String getCodigoSeccion() {
        return codigoSeccion;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public int getCupoOcupado() {
        return cupoOcupado;
    }

    public void setCupoOcupado(int cupoOcupado) {
        if (cupoOcupado >= 0 && cupoOcupado <= cupoMaximo) {
            this.cupoOcupado = cupoOcupado;
        }
    }
}