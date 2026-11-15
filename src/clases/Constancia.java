package clases;

import java.util.Date;

public class Constancia {

    private int idConstancia;
    private String codigoEstudiante;
    private String tipo;
    private Date fechaEmision;
    private String codigoVerificacion;

    public Constancia() {
    }

    public Constancia(int idConstancia, String codigoEstudiante, String tipo, Date fechaEmision, String codigoVerificacion) {
        this.idConstancia = idConstancia;
        this.codigoEstudiante = codigoEstudiante;
        this.tipo = tipo;
        this.fechaEmision = fechaEmision;
        this.codigoVerificacion = codigoVerificacion;
    }

    public int getIdConstancia() {
        return idConstancia;
    }

    public void setIdConstancia(int idConstancia) {
        this.idConstancia = idConstancia;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }
}
