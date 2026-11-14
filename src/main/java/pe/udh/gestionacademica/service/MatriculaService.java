package pe.udh.gestionacademica.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pe.udh.gestionacademica.exception.CupoAgotadoException;
import pe.udh.gestionacademica.model.Estudiante;
import pe.udh.gestionacademica.model.Matricula;
import pe.udh.gestionacademica.model.Seccion;

public class MatriculaService {

    private final Map<String, Estudiante> estudiantes = new LinkedHashMap<>();
    private final Map<Integer, Seccion> secciones = new LinkedHashMap<>();
    private final List<Matricula> matriculas = new ArrayList<>();
    private int siguienteId = 1;

    public void registrarEstudiante(Estudiante estudiante) {
        if (estudiantes.containsKey(estudiante.getCodigo())) {
            throw new IllegalArgumentException(
                    "Ya existe un estudiante con el codigo " + estudiante.getCodigo());
        }
        estudiantes.put(estudiante.getCodigo(), estudiante);
    }

    public void registrarSeccion(Seccion seccion) {
        if (secciones.containsKey(seccion.getId())) {
            throw new IllegalArgumentException("Ya existe la seccion " + seccion.getId());
        }
        secciones.put(seccion.getId(), seccion);
    }

    public Matricula matricular(String codigoEstudiante, int idSeccion)
            throws CupoAgotadoException {
        Estudiante estudiante = estudiantes.get(codigoEstudiante);
        if (estudiante == null) {
            throw new IllegalArgumentException("No existe el estudiante " + codigoEstudiante);
        }
        Seccion seccion = secciones.get(idSeccion);
        if (seccion == null) {
            throw new IllegalArgumentException("No existe la seccion " + idSeccion);
        }
        boolean yaMatriculado = matriculas.stream()
                .anyMatch(matricula -> matricula.getEstudiante().getCodigo()
                        .equals(codigoEstudiante)
                        && matricula.getSeccion().getId() == idSeccion);
        if (yaMatriculado) {
            throw new IllegalArgumentException(
                    "El estudiante ya esta matriculado en la seccion " + idSeccion);
        }
        if (!seccion.hayCupo()) {
            throw new CupoAgotadoException(
                    "La seccion " + idSeccion + " ya no tiene cupos disponibles");
        }
        seccion.ocuparCupo();
        Matricula matricula = new Matricula(
                siguienteId++, estudiante, seccion, LocalDateTime.now());
        matriculas.add(matricula);
        return matricula;
    }

    public List<Matricula> listar() {
        return new ArrayList<>(matriculas);
    }
}
