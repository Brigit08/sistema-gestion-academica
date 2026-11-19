package pe.udh.gestionacademica.exception;

public class CruceHorarioException extends GestionAcademicaException {

    private final int idSeccion;
    private final int idSeccionExistente;

    public CruceHorarioException(int idSeccion, int idSeccionExistente) {
        super("La seccion " + idSeccion + " se cruza con la seccion "
                + idSeccionExistente + " en el horario del estudiante");
        this.idSeccion = idSeccion;
        this.idSeccionExistente = idSeccionExistente;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public int getIdSeccionExistente() {
        return idSeccionExistente;
    }
}
