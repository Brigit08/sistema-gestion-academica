package pe.udh.gestionacademica.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import pe.udh.gestionacademica.exception.ReglaAcademicaException;
import pe.udh.gestionacademica.model.ReglaAcademica;
import pe.udh.gestionacademica.model.TipoRegla;
import pe.udh.gestionacademica.util.Constantes;
import pe.udh.gestionacademica.util.Validaciones;

public class MotorReglasAcademicas {

    private final Map<Integer, List<ReglaAcademica>> reglasPorFacultad = new ConcurrentHashMap<>();

    public void registrarRegla(ReglaAcademica regla) {
        Validaciones.noNulo(regla, "regla");
        reglasPorFacultad
                .computeIfAbsent(regla.getIdFacultad(), id -> new CopyOnWriteArrayList<>())
                .add(regla);
    }

    public void validarMatricula(int idFacultad, int totalCreditos, int totalCursos) {
        double maxCreditos = valor(idFacultad, TipoRegla.MAX_CREDITOS,
                Constantes.MAX_CREDITOS_POR_DEFECTO);
        double maxCursos = valor(idFacultad, TipoRegla.MAX_CURSOS,
                Constantes.MAX_CURSOS_POR_DEFECTO);
        if (totalCreditos > maxCreditos) {
            throw new ReglaAcademicaException("La matricula supera el maximo de "
                    + (int) maxCreditos + " creditos permitidos por la facultad");
        }
        if (totalCursos > maxCursos) {
            throw new ReglaAcademicaException("La matricula supera el maximo de "
                    + (int) maxCursos + " cursos permitidos por la facultad");
        }
    }

    public double notaMinimaAprobatoria(int idFacultad) {
        return valor(idFacultad, TipoRegla.NOTA_MINIMA_APROBATORIA,
                Constantes.NOTA_MINIMA_APROBATORIA_DEFECTO);
    }

    public double asistenciaMinima(int idFacultad) {
        return valor(idFacultad, TipoRegla.ASISTENCIA_MINIMA,
                Constantes.ASISTENCIA_MINIMA_DEFECTO);
    }

    public List<ReglaAcademica> listarPorFacultad(int idFacultad) {
        return List.copyOf(reglasPorFacultad.getOrDefault(idFacultad, List.of()));
    }

    private double valor(int idFacultad, TipoRegla tipo, double valorPorDefecto) {
        return reglasPorFacultad.getOrDefault(idFacultad, List.of()).stream()
                .filter(regla -> regla.getTipo() == tipo)
                .mapToDouble(ReglaAcademica::getValor)
                .findFirst()
                .orElse(valorPorDefecto);
    }
}
