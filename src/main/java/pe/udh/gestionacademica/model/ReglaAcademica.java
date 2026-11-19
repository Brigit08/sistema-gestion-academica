package pe.udh.gestionacademica.model;

import java.util.Objects;

public class ReglaAcademica {

    private final int idRegla;
    private final int idFacultad;
    private final TipoRegla tipo;
    private final double valor;
    private final String descripcion;

    public ReglaAcademica(int idRegla, int idFacultad, TipoRegla tipo,
                          double valor, String descripcion) {
        this.idRegla = idRegla;
        this.idFacultad = idFacultad;
        this.tipo = Objects.requireNonNull(tipo, "El tipo de regla es obligatorio");
        this.valor = valor;
        this.descripcion = Objects.requireNonNull(descripcion, "La descripcion es obligatoria");
    }

    public int getIdRegla() {
        return idRegla;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public TipoRegla getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
