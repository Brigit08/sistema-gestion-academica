package pe.udh.gestionacademica.model;

public class Nota {
    private int idNota;
    private int idDetalle;
    private String tipoEvaluacion;
    private double valor;
    private double peso;

    public Nota() {
    }

    public Nota(int idNota, int idDetalle, String tipoEvaluacion, double valor, double peso) {
        this.idNota = idNota;
        this.idDetalle = idDetalle;
        this.tipoEvaluacion = tipoEvaluacion;
        this.valor = valor;
        this.peso = peso;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}