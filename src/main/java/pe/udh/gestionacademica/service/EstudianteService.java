package pe.udh.gestionacademica.service;

import pe.udh.gestionacademica.model.Estudiante;
import java.util.ArrayList;
import java.util.List;

public class EstudianteService {

    private List<Estudiante> estudiantes = new ArrayList<>();

    public void registrar(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public List<Estudiante> listar() {
        return estudiantes;
    }
}