import clases.Seccion;

public class ControlCupos {

    public synchronized boolean verificarCupo(Seccion seccion) {

        return seccion.getCupoOcupado() < seccion.getCupoMaximo();
    }

    public synchronized boolean reservarCupo(Seccion seccion) {

        if (verificarCupo(seccion)) {

            seccion.setCupoOcupado(
                seccion.getCupoOcupado() + 1
            );

            return true;
        }

        return false;
    }

    public synchronized void liberarCupo(Seccion seccion) {

        if (seccion.getCupoOcupado() > 0) {

            seccion.setCupoOcupado(
                seccion.getCupoOcupado() - 1
            );
        }
    }
}
