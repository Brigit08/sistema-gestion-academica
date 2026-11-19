package pe.udh.gestionacademica.model;

import java.time.LocalDate;
import java.util.Objects;

public class PeriodoAcademico {

    private final int idPeriodo;
    private final String nombre;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final LocalDate inicioMatricula;
    private final LocalDate finMatricula;

    public PeriodoAcademico(int idPeriodo, String nombre, LocalDate fechaInicio,
                            LocalDate fechaFin, LocalDate inicioMatricula,
                            LocalDate finMatricula) {
        this.idPeriodo = idPeriodo;
        this.nombre = Objects.requireNonNull(nombre, "El nombre del periodo es obligatorio");
        this.fechaInicio = Objects.requireNonNull(fechaInicio, "La fecha de inicio es obligatoria");
        this.fechaFin = Objects.requireNonNull(fechaFin, "La fecha de fin es obligatoria");
        this.inicioMatricula = Objects.requireNonNull(inicioMatricula, "El inicio de matricula es obligatorio");
        this.finMatricula = Objects.requireNonNull(finMatricula, "El fin de matricula es obligatorio");
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalDate getInicioMatricula() {
        return inicioMatricula;
    }

    public LocalDate getFinMatricula() {
        return finMatricula;
    }

    public boolean estaAbiertaMatricula(LocalDate fecha) {
        return !fecha.isBefore(inicioMatricula) && !fecha.isAfter(finMatricula);
    }
}
