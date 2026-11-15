package clases;

import java.sql.Time;

public class Horario {

    private int idHorario;
    private int idSeccion;
    private int idAula;
    private String diaSemana;
    private Time  horaInicio;
    private Time horaFim;

    public Horario() {
    }

    public Horario(int idHorario, int idSeccion, int idAula, String diaSemana, Time horaInicio, Time horaFim) {
        this.idHorario = idHorario;
        this.idSeccion = idSeccion;
        this.idAula = idAula;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }
}

/**
 * Día y hora de dictado de una sección.
 * Atributos:
 *   - idHorario: int
 *   - idSeccion: int
 *   - idAula: int
 *   - diaSemana: String
 *   - horaInicio: Time
 *   - horaFin: Time
 * Hito 1: solo estructura, sin código.
 */
