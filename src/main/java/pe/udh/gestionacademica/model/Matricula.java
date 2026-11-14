package pe.udh.gestionacademica.model;

import java.time.LocalDateTime;

public class Matricula {

    private final int id;
    private final Estudiante estudiante;
    private final Seccion seccion;
    private final LocalDateTime fecha;

    public Matricula(int id, Estudiante estudiante, Seccion seccion, LocalDateTime fecha) {
        this.id = id;
        this.estudiante = estudiante;
        this.seccion = seccion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Matricula #" + id
                + " | " + estudiante.getNombreCompleto()
                + " | Curso: " + seccion.getCurso().getNombre()
                + " | Seccion: " + seccion.getId()
                + " | Fecha: " + fecha.toLocalDate();
    }
}
