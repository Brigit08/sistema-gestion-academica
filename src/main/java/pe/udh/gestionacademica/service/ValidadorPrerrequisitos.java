package pe.udh.gestionacademica.service;

import pe.udh.gestionacademica.exception.PrerrequisitoNoCumplidoException;
import pe.udh.gestionacademica.model.Curso;
import pe.udh.gestionacademica.model.Estudiante;
import pe.udh.gestionacademica.model.Prerrequisito;
import pe.udh.gestionacademica.util.Validaciones;

public class ValidadorPrerrequisitos {

    public void validar(Estudiante estudiante, Curso curso) {
        Validaciones.noNulo(estudiante, "estudiante");
        Validaciones.noNulo(curso, "curso");
        for (Prerrequisito prerrequisito : curso.getPrerrequisitos()) {
            if (!estudiante.haAprobado(prerrequisito.getIdCursoRequisito())) {
                throw new PrerrequisitoNoCumplidoException(curso.getIdCurso(),
                        prerrequisito.getIdCursoRequisito());
            }
        }
    }

    public boolean cumple(Estudiante estudiante, Curso curso) {
        return curso.getPrerrequisitos().stream()
                .allMatch(prerrequisito -> estudiante.haAprobado(prerrequisito.getIdCursoRequisito()));
    }
}
