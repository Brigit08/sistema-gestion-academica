import java.sql.Time;

public class Horario {

    private int idHorario;
    private int idSeccion;
    private int idAula;
    private String diaSemana;
    private Time horaInicio;
    private Time horaFin;

    public Horario(int idHorario, int idSeccion, int idAula,
                   String diaSemana, Time horaInicio, Time horaFin) {

        this.idHorario = idHorario;
        this.idSeccion = idSeccion;
        this.idAula = idAula;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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

    public String getDiaSemana() {
        return diaSemana;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }
}