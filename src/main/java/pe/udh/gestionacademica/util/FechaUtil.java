package pe.udh.gestionacademica.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class FechaUtil {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private FechaUtil() {
    }

    public static String formatear(LocalDate fecha) {
        return fecha.format(FORMATO_FECHA);
    }

    public static String formatear(LocalDateTime fecha) {
        return fecha.format(FORMATO_FECHA_HORA);
    }
}
