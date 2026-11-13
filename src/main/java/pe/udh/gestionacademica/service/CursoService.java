package pe.udh.gestionacademica.service;

import pe.udh.gestionacademica.model.Curso;
import java.util.ArrayList;
import java.util.List;

public class CursoService {

    private List<Curso> cursos = new ArrayList<>();

    public void registrar(Curso curso) {
        cursos.add(curso);
    }

    public List<Curso> listar() {
        return cursos;
    }
}