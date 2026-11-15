package clases;

import java.util.Date;

public class PeriodoAcademico {

    private int idPeriodo;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private Date inicioMatricula;
    private Date finMatricula;

    public PeriodoAcademico() {
    }

    public PeriodoAcademico(int idPeriodo, String nombre, Date fechaInicio, Date fechaFin, Date inicioMatricula, Date finMatricula) {
        this.idPeriodo = idPeriodo;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inicioMatricula = inicioMatricula;
        this.finMatricula = finMatricula;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getInicioMatricula() {
        return inicioMatricula;
    }

    public void setInicioMatricula(Date inicioMatricula) {
        this.inicioMatricula = inicioMatricula;
    }

    public Date getFinMatricula() {
        return finMatricula;
    }

    public void setFinMatricula(Date finMatricula) {
        this.finMatricula = finMatricula;
    }
}
