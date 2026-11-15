
public class DetalleMatricula {

    private int idDetalle;
    private int idMatricula;
    private int idSeccion;
    private String estado;

    public DetalleMatricula(int idDetalle, int idMatricula,
                            int idSeccion, String estado) {

        this.idDetalle = idDetalle;
        this.idMatricula = idMatricula;
        this.idSeccion = idSeccion;
        this.estado = estado;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
