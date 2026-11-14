public class Nota {

    private int idNota;
    private int idDetalle;
    private String tipoEvaluacion;
    private double valor;
    private double peso;

    public Nota(int idNota, int idDetalle, String tipoEvaluacion,
                double valor, double peso) {

        this.idNota = idNota;
        this.idDetalle = idDetalle;
        this.tipoEvaluacion = tipoEvaluacion;
        this.valor = valor;
        this.peso = peso;
    }

    public int getIdNota() {
        return idNota;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public double getValor() {
        return valor;
    }

    public double getPeso() {
        return peso;
    }
}