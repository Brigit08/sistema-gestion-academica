package clases;

import java.util.Date;

public class Matricula {

    private int idMatricula;
    private String codigoEstdiante;
    private int idPeriodo;
    private Date fecha;
    private String estado;
    private int totalCreditos;

    public Matricula() {
    }

    public Matricula(int idMatricula, String codigoEstdiante, int idPeriodo, Date fecha, String estado, int totalCreditos) {
        this.idMatricula = idMatricula;
        this.codigoEstdiante = codigoEstdiante;
        this.idPeriodo = idPeriodo;
        this.fecha = fecha;
        this.estado = estado;
        this.totalCreditos = totalCreditos;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getCodigoEstdiante() {
        return codigoEstdiante;
    }

    public void setCodigoEstdiante(String codigoEstdiante) {
        this.codigoEstdiante = codigoEstdiante;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTotalCreditos() {
        return totalCreditos;
    }

    public void setTotalCreditos(int totalCreditos) {
        this.totalCreditos = totalCreditos;
    }
}

