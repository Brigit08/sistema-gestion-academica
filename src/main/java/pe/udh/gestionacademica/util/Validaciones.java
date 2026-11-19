package pe.udh.gestionacademica.util;

import pe.udh.gestionacademica.exception.DatosInvalidosException;

public final class Validaciones {

    private Validaciones() {
    }

    public static String requerido(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new DatosInvalidosException("El campo " + campo + " es obligatorio");
        }
        return valor.trim();
    }

    public static void noNulo(Object valor, String campo) {
        if (valor == null) {
            throw new DatosInvalidosException("El campo " + campo + " es obligatorio");
        }
    }

    public static int enteroPositivo(int valor, String campo) {
        if (valor <= 0) {
            throw new DatosInvalidosException("El campo " + campo + " debe ser mayor que cero");
        }
        return valor;
    }

    public static double rango(double valor, double minimo, double maximo, String campo) {
        if (valor < minimo || valor > maximo) {
            throw new DatosInvalidosException("El campo " + campo + " debe estar entre "
                    + minimo + " y " + maximo);
        }
        return valor;
    }
}
