package clases;

public class ReglaAcademica {

    private int idRegla;
    private int idFacultad;
    private String tipo;
    private Double valor;
    private String descripcion;

    public ReglaAcademica() {
    }

    public ReglaAcademica(int idRegla, int idFacultad, String tipo, Double valor, String descripcion) {
        this.idRegla = idRegla;
        this.idFacultad = idFacultad;
        this.tipo = tipo;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public int getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(int idRegla) {
        this.idRegla = idRegla;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
