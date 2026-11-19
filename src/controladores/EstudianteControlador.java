
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
            dni == null || dni.isBlank() ||
            correo == null || correo.isBlank() ||
            idCarrera <= 0) {
            return false;
        }

        Estudiante estudiante = new Estudiante(
            codigo, nombres, apellidos, dni, correo, idCarrera, estado
        );

        return servicio.registrar(estudiante);
    }

    public List<Estudiante> listarEstudiantes() {
        return servicio.listar();
    }

    public Estudiante buscarEstudiante(String codigo) {
        return servicio.buscarPorCodigo(codigo);
    }
}
