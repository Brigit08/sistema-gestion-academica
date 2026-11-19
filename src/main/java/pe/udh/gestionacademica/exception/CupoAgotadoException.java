package pe.udh.gestionacademica.exception;

public class CupoAgotadoException extends GestionAcademicaException {

    private final int idSeccion;

    public CupoAgotadoException(int idSeccion) {
        super("La seccion " + idSeccion + " ya no tiene cupos disponibles");
        this.idSeccion = idSeccion;
    }

    public int getIdSeccion() {
        return idSeccion;
    }
}
