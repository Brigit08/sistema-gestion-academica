package clases;

public class Seccion {

    private int idSeccion;
    private int idCurso;
    private int idPeriodo;
    private int idDocente;
    private String codigoSeccion;
    private int cupoMaximo;
    private int cupoOcupado;

    public Seccion() {
    }

    public Seccion(int idSeccion, int idCurso, int idPeriodo, int idDocente, String codigoSeccion, int cupoMaximo, int cupoOcupado) {
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

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getCodigoSeccion() {
        return codigoSeccion;
    }

    public void setCodigoSeccion(String codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getCupoOcupado() {
        return cupoOcupado;
    }

    public void setCupoOcupado(int cupoOcupado) {
        this.cupoOcupado = cupoOcupado;
    }
}