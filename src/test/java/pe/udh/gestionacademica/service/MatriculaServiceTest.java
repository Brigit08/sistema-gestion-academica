package pe.udh.gestionacademica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.udh.gestionacademica.exception.CruceHorarioException;
import pe.udh.gestionacademica.exception.CupoAgotadoException;
import pe.udh.gestionacademica.exception.MatriculaDuplicadaException;
import pe.udh.gestionacademica.exception.PrerrequisitoNoCumplidoException;
import pe.udh.gestionacademica.model.Carrera;
import pe.udh.gestionacademica.model.Curso;
import pe.udh.gestionacademica.model.DiaSemana;
import pe.udh.gestionacademica.model.Estudiante;
import pe.udh.gestionacademica.model.Facultad;
import pe.udh.gestionacademica.model.PeriodoAcademico;
import pe.udh.gestionacademica.model.Seccion;

class MatriculaServiceTest {

    private EstudianteService estudiantes;
    private CursoService cursos;
    private SeccionService secciones;
    private MatriculaService matriculas;

    @BeforeEach
    void prepararEscenario() {
        CatalogoService catalogo = new CatalogoService();
        catalogo.registrarFacultad(new Facultad(1, "Ingenieria"));
        catalogo.registrarCarrera(new Carrera(1, "Sistemas", 1));

        estudiantes = new EstudianteService();
        cursos = new CursoService();
        secciones = new SeccionService(cursos);
        PeriodoAcademicoService periodos = new PeriodoAcademicoService();
        LocalDate hoy = LocalDate.now();
        periodos.registrar(new PeriodoAcademico(1, "2026-II", hoy.minusDays(30),
                hoy.plusDays(120), hoy.minusDays(5), hoy.plusDays(5)));

        cursos.registrar(new Curso(1, "INF101", "Programacion I", 4, 1, 1));
        cursos.registrar(new Curso(2, "INF201", "Programacion II", 4, 2, 1));
        cursos.agregarPrerrequisito(2, 1);

        secciones.registrar(new Seccion(10, 1, 1, 1, "A", 2));
        secciones.agregarHorario(10, 1, DiaSemana.LUNES,
                LocalTime.of(8, 0), LocalTime.of(10, 0));
        secciones.registrar(new Seccion(20, 2, 1, 1, "A", 10));
        secciones.agregarHorario(20, 1, DiaSemana.LUNES,
                LocalTime.of(9, 0), LocalTime.of(11, 0));

        matriculas = new MatriculaService(estudiantes, cursos, secciones, periodos,
                catalogo, new ControlCupos(), new ValidadorPrerrequisitos(),
                new ValidadorCruceHorario(), new MotorReglasAcademicas());
    }

    @Test
    void registraMatriculaYNoDuplicaAlEstudiante() {
        estudiantes.registrar(new Estudiante("20210001", "Ana", "Torres",
                "70000001", "ana@udh.edu.pe", 1, "ACTIVO"));

        matriculas.matricular("20210001", 1, List.of(10));

        assertEquals(1, matriculas.listarPorEstudiante("20210001").size());
        assertEquals(1, secciones.buscar(10).getCupoOcupado());
        assertThrows(MatriculaDuplicadaException.class,
                () -> matriculas.matricular("20210001", 1, List.of(10)));
    }

    @Test
    void rechazaMatriculaSinPrerrequisito() {
        estudiantes.registrar(new Estudiante("20210002", "Luis", "Ramos",
                "70000002", "luis@udh.edu.pe", 1, "ACTIVO"));

        assertThrows(PrerrequisitoNoCumplidoException.class,
                () -> matriculas.matricular("20210002", 1, List.of(20)));
    }

    @Test
    void rechazaCruceDeHorario() {
        estudiantes.registrar(new Estudiante("20210003", "Carla", "Diaz",
                "70000003", "carla@udh.edu.pe", 1, "ACTIVO"));
        estudiantes.aprobarCurso("20210003", 1);

        assertThrows(CruceHorarioException.class,
                () -> matriculas.matricular("20210003", 1, List.of(10, 20)));
    }

    @Test
    void noSobrepasaCupoConMatriculasSimultaneas() throws InterruptedException {
        secciones.registrar(new Seccion(99, 1, 1, 1, "C", 5));
        for (int i = 1; i <= 20; i++) {
            estudiantes.registrar(new Estudiante("300000" + i, "Alumno", "Numero" + i,
                    "800000" + i, "alumno" + i + "@udh.edu.pe", 1, "ACTIVO"));
        }

        ExecutorService pool = Executors.newFixedThreadPool(10);
        CountDownLatch inicio = new CountDownLatch(1);
        CountDownLatch fin = new CountDownLatch(20);
        AtomicInteger exitos = new AtomicInteger();
        AtomicInteger rechazos = new AtomicInteger();
        AtomicInteger errores = new AtomicInteger();

        for (int i = 1; i <= 20; i++) {
            String codigo = "300000" + i;
            pool.submit(() -> {
                try {
                    inicio.await();
                    matriculas.matricular(codigo, 1, List.of(99));
                    exitos.incrementAndGet();
                } catch (CupoAgotadoException excepcion) {
                    rechazos.incrementAndGet();
                } catch (Exception excepcion) {
                    errores.incrementAndGet();
                } finally {
                    fin.countDown();
                }
            });
        }

        inicio.countDown();
        fin.await(10, TimeUnit.SECONDS);
        pool.shutdown();

        assertEquals(5, exitos.get());
        assertEquals(15, rechazos.get());
        assertEquals(0, errores.get());
        assertEquals(5, secciones.buscar(99).getCupoOcupado());
    }
}
