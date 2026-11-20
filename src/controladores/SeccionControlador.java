package controladores;
import clases.Seccion;
import java.util.ArrayList;
import java.util.List;

public class SeccionControlador {

    private List<Seccion> secciones = new ArrayList<>();

    public String registrarSeccion(int id, int idCurso, int idPeriodo,
                                  int idDocente, String codigo, int cupoMaximo) {

        if (buscarSeccion(id) != null) {
            return "La sección ya existe.";
        }

        if (cupoMaximo <= 0) {
            return "El cupo debe ser mayor que cero.";
        }

        Seccion seccion = new Seccion(
            id, idCurso, idPeriodo, idDocente, codigo, cupoMaximo, 0
        );

        secciones.add(seccion);
        return "Sección registrada correctamente.";
    }

    public List<Seccion> listarSecciones() {
        return secciones;
    }

    public Seccion buscarSeccion(int id) {
        for (Seccion seccion : secciones) {
            if (seccion.getIdSeccion() == id) {
                return seccion;
            }
        }
        return null;
    }

    public int consultarCupos(int id) {
        Seccion seccion = buscarSeccion(id);

        if (seccion == null) {
            return -1;
        }

        return seccion.getCupoMaximo() - seccion.getCupoOcupado();
    }
}