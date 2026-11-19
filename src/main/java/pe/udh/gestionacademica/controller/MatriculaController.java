package pe.udh.gestionacademica.controller;

import java.util.List;
import pe.udh.gestionacademica.model.Matricula;
import pe.udh.gestionacademica.service.MatriculaService;

public class MatriculaController {

    private final MatriculaService matriculas;

    public MatriculaController(MatriculaService matriculas) {
        this.matriculas = matriculas;
    }

    public Matricula matricular(String codigoEstudiante, int idPeriodo,
                                List<Integer> idsSecciones) {
        return matriculas.matricular(codigoEstudiante, idPeriodo, idsSecciones);
    }

    public Matricula anular(int idMatricula) {
        return matriculas.anular(idMatricula);
    }

    public List<Matricula> listarPorEstudiante(String codigoEstudiante) {
        return matriculas.listarPorEstudiante(codigoEstudiante);
    }
}
