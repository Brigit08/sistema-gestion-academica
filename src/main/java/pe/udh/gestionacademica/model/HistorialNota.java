package pe.udh.gestionacademica.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class HistorialNota {

    private final int idHistorial;
    private final int idNota;
    private final double valorAnterior;
    private final double valorNuevo;
    private final int idUsuario;
    private final LocalDateTime fechaCambio;
    private final String motivo;

    public HistorialNota(int idHistorial, int idNota, double valorAnterior,
                         double valorNuevo, int idUsuario, LocalDateTime fechaCambio,
                         String motivo) {
        this.idHistorial = idHistorial;
        this.idNota = idNota;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
        this.idUsuario = idUsuario;
        this.fechaCambio = Objects.requireNonNull(fechaCambio, "La fecha de cambio es obligatoria");
        this.motivo = Objects.requireNonNull(motivo, "El motivo es obligatorio");
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public int getIdNota() {
        return idNota;
    }

    public double getValorAnterior() {
        return valorAnterior;
    }

    public double getValorNuevo() {
        return valorNuevo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public LocalDateTime getFechaCambio() {
        return fechaCambio;
    }

    public String getMotivo() {
        return motivo;
    }
}
