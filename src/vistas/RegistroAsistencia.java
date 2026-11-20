package vistas;

import clases.Estudiante;

public class RegistroAsistencia {

    private int id;
    private Estudiante estudiante;
    private String fecha;
    private String estado;

    public RegistroAsistencia() {
    }

    public RegistroAsistencia(int id, Estudiante estudiante, String fecha, String estado) {
        this.id = id;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}