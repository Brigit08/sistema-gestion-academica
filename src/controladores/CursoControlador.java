package controladores;

import clases.Curso;
import java.util.ArrayList;
import java.util.List;

public class CursoControlador {

    private List<Curso> cursos = new ArrayList<>();

    public String registrarCurso(int id, String codigo, String nombre,
                                int creditos, int ciclo, int idCarrera) {
        Curso curso = new Curso(id, codigo, nombre, creditos, ciclo, idCarrera);
        cursos.add(curso);

        return "Curso registrado correctamente.";
    }

    public List<Curso> listarCursos() {
        return cursos;
    }

    public Curso buscarCurso(int id) {
        for (Curso curso : cursos) {
            if (curso.getIdCurso() == id) {
                return curso;
            }
        }
        return null;
    }
}