
package servicios;
import clases.Estudiante;
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

    public boolean actualizar(Estudiante actualizado) {
        Estudiante actual = buscarPorCodigo(actualizado.getCodigo());

        if (actual == null) {
            return false;
        }

        Estudiante duplicado = buscarPorDni(actualizado.getDni());

        if (duplicado != null && duplicado != actual) {
            return false;
        }

        actual.setNombres(actualizado.getNombres());
        actual.setApellidos(actualizado.getApellidos());
        actual.setDni(actualizado.getDni());
        actual.setCorreo(actualizado.getCorreo());
        actual.setIdCarrera(actualizado.getIdCarrera());
        actual.setEstado(actualizado.getEstado());

        return true;
    }

    public boolean desactivar(String codigo) {
        Estudiante estudiante = buscarPorCodigo(codigo);

        if (estudiante == null) {
            return false;
        }

        estudiante.setEstado("Inactivo");
        return true;
    }
}
