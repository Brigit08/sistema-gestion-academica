package pe.udh.gestionacademica;

import pe.udh.gestionacademica.model.*;
import pe.udh.gestionacademica.service.*;

public class Main {

    public static void main(String[] args) {

        EstudianteService estudiantes = new EstudianteService();
        CursoService cursos = new CursoService();

        estudiantes.registrar(new Estudiante(
            1, "Benji", "Del Aguila", "12345678", "benji@gmail.com"
        ));

        cursos.registrar(new Curso(1, "Programacion", 4));

        System.out.println("=== MODULO ESTUDIANTES ===");
        estudiantes.listar().forEach(e ->
            System.out.println(e.getNombres() + " " + e.getApellidos())
        );

        System.out.println("=== MODULO CURSOS ===");
        cursos.listar().forEach(c ->
            System.out.println(c.getNombre() + " - " + c.getCreditos() + " creditos")
        );
    }
}