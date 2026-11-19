package pe.udh.gestionacademica.model;

import java.util.Objects;

public class Nota {

    public static final double NOTA_MINIMA = 0.0;
    public static final double NOTA_MAXIMA = 20.0;

    private final int idNota;
    private final int idDetalle;
    private final String tipoEvaluacion;
    private final double peso;
    private double valor;

    public Nota(int idNota, int idDetalle, String tipoEvaluacion,
                double valor, double peso) {
        this.idNota = idNota;
        this.idDetalle = idDetalle;
        this.tipoEvaluacion = Objects.requireNonNull(tipoEvaluacion, "El tipo de evaluacion es obligatorio");
        validarPeso(peso);
        validarValor(valor);
        this.peso = peso;
        this.valor = valor;
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

    public double getPeso() {
        return peso;
    }

    public double getValor() {
        return valor;
    }

    public void actualizarValor(double nuevoValor) {
        validarValor(nuevoValor);
        this.valor = nuevoValor;
    }

    private static void validarValor(double valor) {
        if (valor < NOTA_MINIMA || valor > NOTA_MAXIMA) {
            throw new IllegalArgumentException("La nota debe estar entre "
                    + NOTA_MINIMA + " y " + NOTA_MAXIMA);
        }
    }

    private static void validarPeso(double peso) {
        if (peso <= 0 || peso > 100) {
            throw new IllegalArgumentException("El peso debe estar entre 0 y 100");
        }
    }
}
