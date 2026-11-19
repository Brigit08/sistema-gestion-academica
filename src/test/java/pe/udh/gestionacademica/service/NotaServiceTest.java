package pe.udh.gestionacademica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.udh.gestionacademica.model.Carrera;
import pe.udh.gestionacademica.model.Curso;
import pe.udh.gestionacademica.model.DiaSemana;
import pe.udh.gestionacademica.model.Estudiante;
import pe.udh.gestionacademica.model.Facultad;
import pe.udh.gestionacademica.model.HistorialNota;
import pe.udh.gestionacademica.model.Matricula;
import pe.udh.gestionacademica.model.PeriodoAcademico;
import pe.udh.gestionacademica.model.Seccion;

class NotaServiceTest {

    private NotaService notas;
    private int idDetalle;

    @BeforeEach
    void prepararEscenario() {
        CatalogoService catalogo = new CatalogoService();
        catalogo.registrarFacultad(new Facultad(1, "Ingenieria"));
        catalogo.registrarCarrera(new Carrera(1, "Sistemas", 1));

        EstudianteService estudiantes = new EstudianteService();
        CursoService cursos = new CursoService();
        SeccionService secciones = new SeccionService(cursos);
        PeriodoAcademicoService periodos = new PeriodoAcademicoService();
        LocalDate hoy = LocalDate.now();
        periodos.registrar(new PeriodoAcademico(1, "2026-II", hoy.minusDays(30),
                hoy.plusDays(120), hoy.minusDays(5), hoy.plusDays(5)));

        cursos.registrar(new Curso(1, "INF101", "Programacion I", 4, 1, 1));
        secciones.registrar(new Seccion(10, 1, 1, 1, "A", 10));
        secciones.agregarHorario(10, 1, DiaSemana.LUNES,
                LocalTime.of(8, 0), LocalTime.of(10, 0));

        estudiantes.registrar(new Estudiante("20210001", "Ana", "Torres",
                "70000001", "ana@udh.edu.pe", 1, "ACTIVO"));

        MatriculaService matriculas = new MatriculaService(estudiantes, cursos, secciones,
                periodos, catalogo, new ControlCupos(), new ValidadorPrerrequisitos(),
                new ValidadorCruceHorario(), new MotorReglasAcademicas());
        Matricula matricula = matriculas.matricular("20210001", 1, List.of(10));
        idDetalle = matricula.getDetalles().get(0).getIdDetalle();
        notas = new NotaService(matriculas, new AuditoriaNotas());
    }

    @Test
    void calculaPromedioPonderado() {
        notas.registrar(idDetalle, "Parcial", 15, 40, 1);
        notas.registrar(idDetalle, "Final", 18, 60, 1);

        assertEquals(16.8, notas.promedioPonderado(idDetalle));
    }

    @Test
    void registraValorAnteriorYNuevoEnElHistorial() {
        var parcial = notas.registrar(idDetalle, "Parcial", 15, 40, 1);
        notas.actualizar(parcial.getIdNota(), 17, 1, "Correccion del docente");

        List<HistorialNota> historial = notas.historial(parcial.getIdNota());
        HistorialNota ultimo = historial.get(historial.size() - 1);

        assertEquals(17, notas.buscar(parcial.getIdNota()).getValor());
        assertEquals(2, historial.size());
        assertEquals(15, ultimo.getValorAnterior());
        assertEquals(17, ultimo.getValorNuevo());
        assertEquals(1, ultimo.getIdUsuario());
        assertEquals("Correccion del docente", ultimo.getMotivo());
    }
}
