package pe.udh.gestionacademica.model;

import java.util.Objects;

public class DetalleMatricula {

    public static final String ESTADO_ACTIVO = "ACTIVO";
    public static final String ESTADO_RETIRADO = "RETIRADO";

    private final int idDetalle;
    private final int idMatricula;
    private final int idSeccion;
    private String estado;

    public DetalleMatricula(int idDetalle, int idMatricula, int idSeccion, String estado) {
        this.idDetalle = idDetalle;
        this.idMatricula = idMatricula;
        this.idSeccion = idSeccion;
        this.estado = Objects.requireNonNull(estado, "El estado es obligatorio");
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

    public void retirar() {
        this.estado = ESTADO_RETIRADO;
    }

    public boolean estaActivo() {
        return ESTADO_ACTIVO.equalsIgnoreCase(estado);
    }
}
