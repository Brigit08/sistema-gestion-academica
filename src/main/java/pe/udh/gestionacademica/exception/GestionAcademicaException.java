package pe.udh.gestionacademica.exception;

public class GestionAcademicaException extends RuntimeException {

    public GestionAcademicaException(String mensaje) {
        super(mensaje);
    }

    public GestionAcademicaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
