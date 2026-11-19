package pe.udh.gestionacademica.model;

import java.time.LocalDate;
import java.util.Objects;

public class Constancia {

    private final int idConstancia;
    private final String codigoEstudiante;
    private final String tipo;
    private final LocalDate fechaEmision;
    private final String codigoVerificacion;

    public Constancia(int idConstancia, String codigoEstudiante, String tipo,
                      LocalDate fechaEmision, String codigoVerificacion) {
        this.idConstancia = idConstancia;
        this.codigoEstudiante = Objects.requireNonNull(codigoEstudiante, "El codigo del estudiante es obligatorio");
        this.tipo = Objects.requireNonNull(tipo, "El tipo de constancia es obligatorio");
        this.fechaEmision = Objects.requireNonNull(fechaEmision, "La fecha de emision es obligatoria");
        this.codigoVerificacion = Objects.requireNonNull(codigoVerificacion, "El codigo de verificacion es obligatorio");
    }

    public int getIdConstancia() {
        return idConstancia;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }
}
