
package controladores;

import clases.Estudiante;
import servicios.EstudianteService;
import java.util.List;

public class EstudianteControlador {

    private EstudianteService servicio;

    public EstudianteControlador(EstudianteService servicio) {
        this.servicio = servicio;
    }

    public boolean registrarEstudiante(String codigo, String nombres,
            String apellidos, String dni, String correo,
            int idCarrera, String estado) {

        if (codigo == null || codigo.isBlank() ||
            nombres == null || nombres.isBlank() ||
            apellidos == null || apellidos.isBlank() ||
            dni == null || !dni.matches("\\d{8}") ||
            correo == null || !correo.trim().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$") ||
            idCarrera <= 0 ||
            estado == null || estado.isBlank()) {
            return false;
        }

        Estudiante estudiante = new Estudiante(
            codigo.trim(), nombres.trim(), apellidos.trim(),
            dni.trim(), correo.trim(), idCarrera, estado.trim()
        );

        return servicio.registrar(estudiante);
    }

    public boolean actualizarEstudiante(String codigo, String nombres,
            String apellidos, String dni, String correo,
            int idCarrera, String estado) {

        if (codigo == null || codigo.isBlank() ||
            nombres == null || nombres.isBlank() ||
            apellidos == null || apellidos.isBlank() ||
            dni == null || !dni.matches("\\d{8}") ||
            correo == null || !correo.trim().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$") ||
            idCarrera <= 0 ||
            estado == null || estado.isBlank()) {
            return false;
        }

        Estudiante estudiante = new Estudiante(
            codigo.trim(), nombres.trim(), apellidos.trim(),
            dni.trim(), correo.trim(), idCarrera, estado.trim()
        );

        return servicio.actualizar(estudiante);
    }

    public boolean desactivarEstudiante(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return false;
        }

        return servicio.desactivar(codigo.trim());
    }

    public List<Estudiante> listarEstudiantes() {
        return servicio.listar();
    }

    public Estudiante buscarEstudiante(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return null;
        }

        return servicio.buscarPorCodigo(codigo.trim());
    }
}
