package pe.udh.gestionacademica.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import pe.udh.gestionacademica.exception.DatosInvalidosException;
import pe.udh.gestionacademica.exception.RecursoNoEncontradoException;
import pe.udh.gestionacademica.model.Curso;
import pe.udh.gestionacademica.model.Prerrequisito;
import pe.udh.gestionacademica.util.Validaciones;

public class CursoService {

    private final Map<Integer, Curso> cursos = new ConcurrentHashMap<>();

    public void registrar(Curso curso) {
        Validaciones.noNulo(curso, "curso");
        Validaciones.enteroPositivo(curso.getIdCurso(), "idCurso");
        Validaciones.enteroPositivo(curso.getCreditos(), "creditos");
        Validaciones.enteroPositivo(curso.getCiclo(), "ciclo");
        Validaciones.requerido(curso.getCodigo(), "codigo");
        Validaciones.requerido(curso.getNombre(), "nombre");
        if (cursos.putIfAbsent(curso.getIdCurso(), curso) != null) {
            throw new DatosInvalidosException("Ya existe un curso con id "
                    + curso.getIdCurso());
        }
    }

    public Curso buscar(int idCurso) {
        Curso curso = cursos.get(idCurso);
        if (curso == null) {
            throw new RecursoNoEncontradoException("No existe el curso " + idCurso);
        }
        return curso;
    }

    public List<Curso> listar() {
        return cursos.values().stream()
                .sorted((a, b) -> Integer.compare(a.getIdCurso(), b.getIdCurso()))
                .toList();
    }

    public void agregarPrerrequisito(int idCurso, int idCursoRequisito) {
        Validaciones.enteroPositivo(idCurso, "idCurso");
        Validaciones.enteroPositivo(idCursoRequisito, "idCursoRequisito");
        if (idCurso == idCursoRequisito) {
            throw new DatosInvalidosException("Un curso no puede ser prerrequisito de si mismo");
        }
        Curso curso = buscar(idCurso);
        buscar(idCursoRequisito);
        boolean yaExiste = curso.getPrerrequisitos().stream()
                .map(Prerrequisito::getIdCursoRequisito)
                .anyMatch(id -> id == idCursoRequisito);
        if (yaExiste) {
            throw new DatosInvalidosException("El prerrequisito ya esta registrado");
        }
        curso.agregarPrerrequisito(idCursoRequisito);
    }
}
