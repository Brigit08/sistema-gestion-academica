package pe.udh.gestionacademica.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Matricula {

    public static final String ESTADO_REGISTRADA = "REGISTRADA";
    public static final String ESTADO_ANULADA = "ANULADA";

    private final int idMatricula;
    private final String codigoEstudiante;
    private final int idPeriodo;
    private final LocalDateTime fecha;
    private String estado;
    private int totalCreditos;
    private final List<DetalleMatricula> detalles;

    public Matricula(int idMatricula, String codigoEstudiante, int idPeriodo,
                     LocalDateTime fecha, String estado) {
        this.idMatricula = idMatricula;
        this.codigoEstudiante = Objects.requireNonNull(codigoEstudiante, "El codigo del estudiante es obligatorio");
        this.idPeriodo = idPeriodo;
        this.fecha = Objects.requireNonNull(fecha, "La fecha es obligatoria");
        this.estado = Objects.requireNonNull(estado, "El estado es obligatorio");
        this.detalles = new ArrayList<>();
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public int getTotalCreditos() {
        return totalCreditos;
    }

    public void setTotalCreditos(int totalCreditos) {
        this.totalCreditos = totalCreditos;
    }

    public void agregarDetalle(DetalleMatricula detalle) {
        detalles.add(Objects.requireNonNull(detalle, "El detalle es obligatorio"));
    }

    public List<DetalleMatricula> getDetalles() {
        return Collections.unmodifiableList(detalles);
    }

    public void anular() {
        this.estado = ESTADO_ANULADA;
        detalles.forEach(DetalleMatricula::retirar);
    }

    public boolean estaRegistrada() {
        return ESTADO_REGISTRADA.equalsIgnoreCase(estado);
    }
}
