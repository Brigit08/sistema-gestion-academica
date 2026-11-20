package controladores;

import clases.Nota;
import vistas.RegistroAsistencia;
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
        List<String[]> resultado = new ArrayList<>();

        for (RegistroAsistencia r : asistenciaControlador.listarAsistencias()) {
            if (r.getEstudiante().getCodigo().equals(codigoEstudiante)) {
                resultado.add(new String[]{
                    String.valueOf(r.getId()),
                    r.getEstudiante().getCodigo(),
                    r.getFecha(),
                    r.getEstado()
                });
            }
        }

        return resultado;
    }
}