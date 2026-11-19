package pe.udh.gestionacademica.model;

import java.time.LocalDate;
import java.util.Objects;

public class Asistencia {

    public static final String PRESENTE = "PRESENTE";
    public static final String AUSENTE = "AUSENTE";
    public static final String TARDANZA = "TARDANZA";

    private final int idAsistencia;
    private final int idDetalle;
    private final LocalDate fecha;
    private String estado;

    public Asistencia(int idAsistencia, int idDetalle, LocalDate fecha, String estado) {
        this.idAsistencia = idAsistencia;
        this.idDetalle = idDetalle;
        this.fecha = Objects.requireNonNull(fecha, "La fecha es obligatoria");
        this.estado = Objects.requireNonNull(estado, "El estado es obligatorio");
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = Objects.requireNonNull(estado, "El estado es obligatorio");
    }

    public boolean esPresente() {
        return PRESENTE.equalsIgnoreCase(estado) || TARDANZA.equalsIgnoreCase(estado);
    }
}
