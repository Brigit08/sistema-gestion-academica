package clases;

public class DetalleMatricula {

    private int idDetalle;
    private int idMatricula;
    private int idSeccion;
    private String estado;

    public DetalleMatricula() {
    }

    public DetalleMatricula(int idDetalle, int idMatricula, int idSeccion, String estado) {
        this.idDetalle = idDetalle;
        this.idMatricula = idMatricula;
        this.idSeccion = idSeccion;
        this.estado = estado;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
