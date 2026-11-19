
package servicios;

import clases.Estudiante;
import java.util.ArrayList;
import java.util.List;

public class EstudianteService {

    private List<Estudiante> estudiantes = new ArrayList<>();

    public boolean registrar(Estudiante estudiante) {
        if (buscarPorCodigo(estudiante.getCodigo()) != null ||
            buscarPorDni(estudiante.getDni()) != null) {
            return false;
        }

        estudiantes.add(estudiante);
        return true;
    }

    public List<Estudiante> listar() {
        return new ArrayList<>(estudiantes);
    }

    public Estudiante buscarPorCodigo(String codigo) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCodigo().equals(codigo)) {
                return estudiante;
            }
        }
        return null;
    }

    public Estudiante buscarPorDni(String dni) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getDni().equals(dni)) {
                return estudiante;
            }
        }
        return null;
    }
}
