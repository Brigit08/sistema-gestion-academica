package clases;

import java.util.Date;

public class HistorialNota {

    private int idHistorial;
    private int idNota;
    private Double valorAnterior;
    private Double valorNuevo;
    private int idUsuario;
    private Date fechaCambio;
    private String motivo;

    public HistorialNota() {
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public Double getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(Double valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public Double getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(Double valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}