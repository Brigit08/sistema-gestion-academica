package pe.udh.gestionacademica.model;

import java.time.LocalTime;
import java.util.Objects;

public class HorarioSeccion {

    private final int idHorario;
    private final int idSeccion;
    private final int idAula;
    private final DiaSemana diaSemana;
    private final LocalTime horaInicio;
    private final LocalTime horaFin;

    public HorarioSeccion(int idHorario, int idSeccion, int idAula,
                          DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this.idHorario = idHorario;
        this.idSeccion = idSeccion;
        this.idAula = idAula;
        this.diaSemana = Objects.requireNonNull(diaSemana, "El dia es obligatorio");
        this.horaInicio = Objects.requireNonNull(horaInicio, "La hora de inicio es obligatoria");
        this.horaFin = Objects.requireNonNull(horaFin, "La hora de fin es obligatoria");
        if (!horaInicio.isBefore(horaFin)) {
            throw new IllegalArgumentException("La hora de inicio debe ser menor que la hora de fin");
        }
    }

    public int getIdHorario() {
        return idHorario;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public int getIdAula() {
        return idAula;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public boolean seSolapaCon(HorarioSeccion otro) {
        return diaSemana == otro.diaSemana
                && horaInicio.isBefore(otro.horaFin)
                && otro.horaInicio.isBefore(horaFin);
    }
}
