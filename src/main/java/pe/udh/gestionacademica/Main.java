package pe.udh.gestionacademica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import pe.udh.gestionacademica.controller.MatriculaController;
import pe.udh.gestionacademica.controller.NotaController;
import pe.udh.gestionacademica.exception.GestionAcademicaException;
import pe.udh.gestionacademica.model.Carrera;
import pe.udh.gestionacademica.model.Curso;
import pe.udh.gestionacademica.model.DetalleMatricula;
import pe.udh.gestionacademica.model.DiaSemana;
import pe.udh.gestionacademica.model.Estudiante;
import pe.udh.gestionacademica.model.Facultad;
import pe.udh.gestionacademica.model.Matricula;
import pe.udh.gestionacademica.model.PeriodoAcademico;
import pe.udh.gestionacademica.model.ReglaAcademica;
import pe.udh.gestionacademica.model.Seccion;
import pe.udh.gestionacademica.model.TipoRegla;
import pe.udh.gestionacademica.service.AuditoriaNotas;
import pe.udh.gestionacademica.service.CatalogoService;
import pe.udh.gestionacademica.service.ControlCupos;
import pe.udh.gestionacademica.service.CursoService;
import pe.udh.gestionacademica.service.EstudianteService;
import pe.udh.gestionacademica.service.MatriculaService;
import pe.udh.gestionacademica.service.MotorReglasAcademicas;
import pe.udh.gestionacademica.service.NotaService;
import pe.udh.gestionacademica.service.PeriodoAcademicoService;
import pe.udh.gestionacademica.service.SeccionService;
import pe.udh.gestionacademica.service.ValidadorCruceHorario;
import pe.udh.gestionacademica.service.ValidadorPrerrequisitos;
import pe.udh.gestionacademica.view.VistaHistorialNotas;
import pe.udh.gestionacademica.view.VistaMatricula;
import pe.udh.gestionacademica.view.VistaRegistroNotas;

public class Main {

    private static final int PERIODO = 1;

    public static void main(String[] args) {
        if (args.length > 0 && "--demo".equalsIgnoreCase(args[0])) {
            ejecutarDemoCompleto();
            return;
        }
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;
            do {
                imprimirMenu();
                opcion = leerOpcion(scanner);
                ejecutarOpcion(opcion);
            } while (opcion != 0);
        }
    }

    private static void imprimirMenu() {
        System.out.println("\n=== SISTEMA DE GESTION ACADEMICA - HITO 2 ===");
        System.out.println("1. Modulo de Matricula");
        System.out.println("2. Modulo de Notas con auditoria");
        System.out.println("3. Ejecutar ambos modulos");
        System.out.println("0. Salir");
    }

    private static int leerOpcion(Scanner scanner) {
        System.out.print("Seleccione una opcion: ");
        String linea = scanner.nextLine();
        try {
            return Integer.parseInt(linea.trim());
        } catch (NumberFormatException excepcion) {
            return -1;
        }
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> ejecutarModuloMatricula();
            case 2 -> ejecutarModuloNotas();
            case 3 -> ejecutarDemoCompleto();
            case 0 -> System.out.println("Saliendo del sistema.");
            default -> System.out.println("Opcion no valida. Intente nuevamente.");
        }
    }

    private static void ejecutarModuloMatricula() {
        Contexto contexto = new Contexto();
        System.out.println("=== MODULO DE MATRICULA ===");
        probarMatriculaExitosa(contexto.matriculaController, contexto.vistaMatricula);
        probarPrerrequisito(contexto.matriculaController);
        probarCruceHorario(contexto.matriculaController);
        probarCupoAgotado(contexto.matriculaController);
    }

    private static void ejecutarModuloNotas() {
        Contexto contexto = new Contexto();
        contexto.matricularAna();
        System.out.println("=== MODULO DE NOTAS CON AUDITORIA ===");
        probarNotas(contexto.matriculas, contexto.notaController,
                contexto.vistaNotas, contexto.vistaHistorial);
    }

    private static void ejecutarDemoCompleto() {
        Contexto contexto = new Contexto();
        System.out.println("=== MODULO DE MATRICULA ===");
        probarMatriculaExitosa(contexto.matriculaController, contexto.vistaMatricula);
        probarPrerrequisito(contexto.matriculaController);
        probarCruceHorario(contexto.matriculaController);
        probarCupoAgotado(contexto.matriculaController);
        System.out.println("\n=== MODULO DE NOTAS CON AUDITORIA ===");
        probarNotas(contexto.matriculas, contexto.notaController,
                contexto.vistaNotas, contexto.vistaHistorial);
        System.out.println("\nFin del avance: matricula y notas funcionando.");
    }

    private static void probarMatriculaExitosa(MatriculaController controller,
                                               VistaMatricula vista) {
        System.out.println("\n1) Ana se matricula en Base de Datos y Redes:");
        Matricula matricula = controller.matricular("20210001", PERIODO, List.of(30, 40));
        vista.mostrar(matricula);
    }

    private static void probarPrerrequisito(MatriculaController controller) {
        System.out.println("\n2) Luis intenta POO sin aprobar Introduccion a la Programacion:");
        try {
            controller.matricular("20210002", PERIODO, List.of(20));
        } catch (GestionAcademicaException excepcion) {
            System.out.println("  Rechazado: " + excepcion.getMessage());
        }
    }

    private static void probarCruceHorario(MatriculaController controller) {
        System.out.println("\n3) Carla intenta dos secciones que se cruzan el lunes:");
        try {
            controller.matricular("20210003", PERIODO, List.of(10, 40));
        } catch (GestionAcademicaException excepcion) {
            System.out.println("  Rechazado: " + excepcion.getMessage());
        }
    }

    private static void probarCupoAgotado(MatriculaController controller) {
        System.out.println("\n4) Seccion 10 con cupo para 2 estudiantes:");
        controller.matricular("20210004", PERIODO, List.of(10));
        controller.matricular("20210005", PERIODO, List.of(10));
        System.out.println("  Mario y Sofia ocuparon los 2 cupos.");
        try {
            controller.matricular("20210006", PERIODO, List.of(10));
        } catch (GestionAcademicaException excepcion) {
            System.out.println("  Rechazado: " + excepcion.getMessage());
        }
    }

    private static void probarNotas(MatriculaService matriculas, NotaController controller,
                                    VistaRegistroNotas vistaNotas,
                                    VistaHistorialNotas vistaHistorial) {
        Matricula matricula = matriculas.listarPorEstudiante("20210001").get(0);
        DetalleMatricula detalle = matricula.getDetalles().stream()
                .filter(item -> item.getIdSeccion() == 30)
                .findFirst()
                .orElseThrow();
        int idDetalle = detalle.getIdDetalle();

        var parcial = controller.registrar(idDetalle, "Parcial", 15, 40, 1);
        controller.registrar(idDetalle, "Final", 18, 60, 1);
        System.out.println("\n1) Notas registradas:");
        vistaNotas.mostrar(controller.listarPorDetalle(idDetalle),
                controller.promedio(idDetalle));

        controller.actualizar(parcial.getIdNota(), 17, 1,
                "Correccion de calificacion solicitada por el docente");
        System.out.println("\n2) Notas despues de corregir el parcial:");
        vistaNotas.mostrar(controller.listarPorDetalle(idDetalle),
                controller.promedio(idDetalle));

        System.out.println("\n3) Historial auditable del parcial:");
        vistaHistorial.mostrar(controller.historial(parcial.getIdNota()));
    }

    private static final class Contexto {

        private final CatalogoService catalogo = new CatalogoService();
        private final EstudianteService estudiantes = new EstudianteService();
        private final CursoService cursos = new CursoService();
        private final SeccionService secciones = new SeccionService(cursos);
        private final PeriodoAcademicoService periodos = new PeriodoAcademicoService();
        private final MotorReglasAcademicas motorReglas = new MotorReglasAcademicas();
        private final MatriculaService matriculas;
        private final NotaController notaController;
        private final MatriculaController matriculaController;
        private final VistaMatricula vistaMatricula = new VistaMatricula();
        private final VistaRegistroNotas vistaNotas = new VistaRegistroNotas();
        private final VistaHistorialNotas vistaHistorial = new VistaHistorialNotas();

        private Contexto() {
            this.matriculas = new MatriculaService(estudiantes, cursos, secciones, periodos,
                    catalogo, new ControlCupos(), new ValidadorPrerrequisitos(),
                    new ValidadorCruceHorario(), motorReglas);
            this.notaController = new NotaController(new NotaService(matriculas, new AuditoriaNotas()));
            this.matriculaController = new MatriculaController(matriculas);
            cargarDatos();
        }

        private void matricularAna() {
            matriculaController.matricular("20210001", PERIODO, List.of(30, 40));
        }

        private void cargarDatos() {
            catalogo.registrarFacultad(new Facultad(1, "Ingenieria"));
            catalogo.registrarCarrera(new Carrera(1,
                    "Ingenieria de Sistemas e Informatica", 1));

            LocalDate hoy = LocalDate.now();
            periodos.registrar(new PeriodoAcademico(PERIODO, "2026-II",
                    hoy.minusDays(30), hoy.plusDays(120), hoy.minusDays(5), hoy.plusDays(5)));

            cursos.registrar(new Curso(1, "INF101", "Introduccion a la Programacion", 4, 1, 1));
            cursos.registrar(new Curso(2, "INF201", "Programacion Orientada a Objetos", 4, 2, 1));
            cursos.registrar(new Curso(3, "INF301", "Base de Datos", 4, 3, 1));
            cursos.registrar(new Curso(4, "INF401", "Redes de Computadoras", 3, 4, 1));
            cursos.agregarPrerrequisito(2, 1);
            cursos.agregarPrerrequisito(3, 2);

            secciones.registrar(new Seccion(10, 1, PERIODO, 1, "A", 2));
            secciones.agregarHorario(10, 1, DiaSemana.LUNES,
                    LocalTime.of(8, 0), LocalTime.of(10, 0));
            secciones.registrar(new Seccion(11, 1, PERIODO, 1, "B", 10));
            secciones.agregarHorario(11, 1, DiaSemana.MARTES,
                    LocalTime.of(10, 0), LocalTime.of(12, 0));
            secciones.registrar(new Seccion(20, 2, PERIODO, 2, "A", 10));
            secciones.agregarHorario(20, 2, DiaSemana.LUNES,
                    LocalTime.of(9, 0), LocalTime.of(11, 0));
            secciones.registrar(new Seccion(30, 3, PERIODO, 3, "A", 10));
            secciones.agregarHorario(30, 3, DiaSemana.MIERCOLES,
                    LocalTime.of(8, 0), LocalTime.of(10, 0));
            secciones.registrar(new Seccion(40, 4, PERIODO, 4, "A", 10));
            secciones.agregarHorario(40, 4, DiaSemana.LUNES,
                    LocalTime.of(9, 0), LocalTime.of(11, 0));

            estudiantes.registrar(new Estudiante("20210001", "Ana", "Torres",
                    "70000001", "ana@udh.edu.pe", 1, "ACTIVO"));
            estudiantes.aprobarCurso("20210001", 1);
            estudiantes.aprobarCurso("20210001", 2);
            estudiantes.registrar(new Estudiante("20210002", "Luis", "Ramos",
                    "70000002", "luis@udh.edu.pe", 1, "ACTIVO"));
            estudiantes.registrar(new Estudiante("20210003", "Carla", "Diaz",
                    "70000003", "carla@udh.edu.pe", 1, "ACTIVO"));
            estudiantes.registrar(new Estudiante("20210004", "Mario", "Pena",
                    "70000004", "mario@udh.edu.pe", 1, "ACTIVO"));
            estudiantes.registrar(new Estudiante("20210005", "Sofia", "Rojas",
                    "70000005", "sofia@udh.edu.pe", 1, "ACTIVO"));
            estudiantes.registrar(new Estudiante("20210006", "Diego", "Vargas",
                    "70000006", "diego@udh.edu.pe", 1, "ACTIVO"));

            motorReglas.registrarRegla(new ReglaAcademica(1, 1, TipoRegla.MAX_CREDITOS,
                    22, "Maximo de creditos por matricula"));
            motorReglas.registrarRegla(new ReglaAcademica(2, 1, TipoRegla.MAX_CURSOS,
                    6, "Maximo de cursos por matricula"));
            motorReglas.registrarRegla(new ReglaAcademica(3, 1, TipoRegla.NOTA_MINIMA_APROBATORIA,
                    10.5, "Nota minima aprobatoria"));
        }
    }
}
