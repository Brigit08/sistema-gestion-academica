package PruebasMain;

import clases.Estudiante;
import controladores.AsistenciaControlador;
import vistas.RegistroAsistencia;

public class MainAsistencia {

    public static void main(String[] args) {
        AsistenciaControlador controlador = new AsistenciaControlador();

        // Insertar datos de estudiante
        Estudiante est1 = new Estudiante(
                "2026001", "Ana Lucia", "Torres Vega",
                "74125896", "ana.torres@udh.edu.pe", 1, "ACTIVO"
        );

        Estudiante est2 = new Estudiante(
                "2026002", "Luis Alberto", "Mendoza Rios",
                "78945612", "luis.mendoza@udh.edu.pe", 1, "ACTIVO"
        );

        // registrar asistenciaa
        RegistroAsistencia reg1 = new RegistroAsistencia();
        reg1.setId(1);
        reg1.setEstudiante(est1);
        reg1.setFecha("2026-11-20");
        reg1.setEstado("PRESENTE");

        RegistroAsistencia reg2 = new RegistroAsistencia();
        reg2.setId(2);
        reg2.setEstudiante(est2);
        reg2.setFecha("2026-11-20");
        reg2.setEstado("TARDANZA");

        RegistroAsistencia regDuplicado = new RegistroAsistencia();
        regDuplicado.setId(1);
        regDuplicado.setEstudiante(est1);
        regDuplicado.setFecha("2026-11-20");
        regDuplicado.setEstado("FALTA");

        // Registrar asistencias
        System.out.println("Registro 1 agregado: " + controlador.registrarAsistencia(reg1));
        System.out.println("Registro 2 agregado: " + controlador.registrarAsistencia(reg2));
        System.out.println("Intento duplicado (ID 1): " + controlador.registrarAsistencia(regDuplicado));

        // Muestra lista de asistencia
        System.out.println("\n--- Lista de asistencias ---");
        for (RegistroAsistencia r : controlador.listarAsistencias()) {
            imprimirRegistro(r);
        }

        // Búsqueda por ID
        System.out.println("\n--- Buscar ID ---");
        RegistroAsistencia encontradoId = controlador.buscarPorId(2);
        if (encontradoId != null) {
            imprimirRegistro(encontradoId);
        } else {
            System.out.println("No se encontró el registro con ID 2");
        }

        // Búsqueda por fecha
        System.out.println("\n--- Buscar fecha 2026-11-20 ---");
        for (RegistroAsistencia r : controlador.buscarPorFecha("2026-11-20")) {
            imprimirRegistro(r);
        }

        // Actualiza asistencia
        System.out.println("\n--- Actualizar estado ID 2 ---");
        boolean actualizado = controlador.actualizarEstado(2, "PRESENTE");
        System.out.println("Actualizado: " + actualizado);

        RegistroAsistencia actualizadoReg = controlador.buscarPorId(2);
        if (actualizadoReg != null) {
            System.out.print("Nuevo valor -> ");
            imprimirRegistro(actualizadoReg);
        }

        // Elimina asistencia registrada
        System.out.println("\n--- Eliminar ID 1 ---");
        System.out.println("Eliminado: " + controlador.eliminarAsistencia(1));
        System.out.println("Total de registros restantes: " + controlador.listarAsistencias().size());
    }

    private static void imprimirRegistro(RegistroAsistencia r) {
        Estudiante est = r.getEstudiante();
        String codigo = (est != null) ? est.getCodigo() : "N/A";
        String nombreCompleto = (est != null)
                ? est.getNombres() + " " + est.getApellidos()
                : "Sin estudiante";

        System.out.println(
                "ID: " + r.getId() +
                        " | Código: " + codigo +
                        " | Estudiante: " + nombreCompleto +
                        " | Fecha: " + r.getFecha() +
                        " | Estado: " + r.getEstado()
        );
    }
}