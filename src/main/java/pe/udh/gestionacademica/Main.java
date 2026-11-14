package pe.udh.gestionacademica;

import pe.udh.gestionacademica.exception.CupoAgotadoException;
import pe.udh.gestionacademica.model.Curso;
import pe.udh.gestionacademica.model.Estudiante;
import pe.udh.gestionacademica.model.Matricula;
import pe.udh.gestionacademica.model.Seccion;
import pe.udh.gestionacademica.service.MatriculaService;

public class Main {

    public static void main(String[] args) {
        MatriculaService servicio = new MatriculaService();
        Curso curso = new Curso("INF101", "Introduccion a la Programacion", 4);
        Seccion seccion = new Seccion(1, curso, 2);

        servicio.registrarSeccion(seccion);
        servicio.registrarEstudiante(new Estudiante("20210001", "Ana", "Torres"));
        servicio.registrarEstudiante(new Estudiante("20210002", "Luis", "Ramos"));
        servicio.registrarEstudiante(new Estudiante("20210003", "Carla", "Diaz"));

        System.out.println("=== MATRICULA: AVANCE MINIMO ===");

        try {
            Matricula primera = servicio.matricular("20210001", 1);
            System.out.println("Registrada: " + primera);
            Matricula segunda = servicio.matricular("20210002", 1);
            System.out.println("Registrada: " + segunda);
        } catch (CupoAgotadoException excepcion) {
            System.out.println("Error: " + excepcion.getMessage());
        }

        try {
            servicio.matricular("20210003", 1);
        } catch (CupoAgotadoException excepcion) {
            System.out.println("Rechazada: " + excepcion.getMessage());
        }

        try {
            servicio.matricular("20210001", 1);
        } catch (IllegalArgumentException excepcion) {
            System.out.println("Rechazada: " + excepcion.getMessage());
        } catch (CupoAgotadoException excepcion) {
            System.out.println("Rechazada: " + excepcion.getMessage());
        }

        System.out.println("Matriculas registradas: " + servicio.listar().size());
        servicio.listar().forEach(System.out::println);
    }
}
