package pe.udh.gestionacademica.service;

import pe.udh.gestionacademica.exception.CupoAgotadoException;
import pe.udh.gestionacademica.model.Seccion;
import pe.udh.gestionacademica.util.Validaciones;

public class ControlCupos {

    public void reservar(Seccion seccion) {
        Validaciones.noNulo(seccion, "seccion");
        if (!seccion.reservarCupo()) {
            throw new CupoAgotadoException(seccion.getIdSeccion());
        }
    }

    public void liberar(Seccion seccion) {
        Validaciones.noNulo(seccion, "seccion");
        seccion.liberarCupo();
    }
}
