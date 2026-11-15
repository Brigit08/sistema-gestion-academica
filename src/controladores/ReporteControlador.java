import clases.Nota;
import java.util.ArrayList;
import java.util.List;

public class ReporteControlador {

    private NotaControlador notaControlador;
    private AsistenciaControlador asistenciaControlador;

    public ReporteControlador(NotaControlador notaControlador,
                              AsistenciaControlador asistenciaControlador) {
        this.notaControlador = notaControlador;
        this.asistenciaControlador = asistenciaControlador;
    }

    public List<Nota> reporteNotas(int idDetalle) {
        List<Nota> resultado = new ArrayList<>();

        for (Nota nota : notaControlador.listarNotas()) {
            if (nota.getIdDetalle() == idDetalle) {
                resultado.add(nota);
            }
        }

        return resultado;
    }

    public List<String[]> reporteAsistencia(String codigoEstudiante) {
        return asistenciaControlador.buscarPorEstudiante(codigoEstudiante);
    }
}