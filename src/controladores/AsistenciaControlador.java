import java.util.ArrayList;
import java.util.List;

public class AsistenciaControlador {

    private List<String[]> asistencias = new ArrayList<>();

    public String registrarAsistencia(String codigoEstudiante, int idSeccion,
                                      String fecha, String estado) {
        asistencias.add(new String[]{codigoEstudiante, String.valueOf(idSeccion), fecha, estado});
        return "Asistencia registrada correctamente.";
    }

    public List<String[]> listarAsistencias() {
        return asistencias;
    }

    public List<String[]> buscarPorEstudiante(String codigoEstudiante) {
        List<String[]> resultado = new ArrayList<>();
        for (String[] asistencia : asistencias) {
            if (asistencia[0].equals(codigoEstudiante)) {
                resultado.add(asistencia);
            }
        }
        return resultado;
    }
}