package pe.udh.gestionacademica.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import pe.udh.gestionacademica.exception.DatosInvalidosException;
import pe.udh.gestionacademica.exception.PeriodoMatriculaCerradoException;
import pe.udh.gestionacademica.exception.RecursoNoEncontradoException;
import pe.udh.gestionacademica.model.PeriodoAcademico;
import pe.udh.gestionacademica.util.Validaciones;

public class PeriodoAcademicoService {

    private final Map<Integer, PeriodoAcademico> periodos = new ConcurrentHashMap<>();

    public void registrar(PeriodoAcademico periodo) {
        Validaciones.noNulo(periodo, "periodo");
        Validaciones.enteroPositivo(periodo.getIdPeriodo(), "idPeriodo");
        if (periodos.putIfAbsent(periodo.getIdPeriodo(), periodo) != null) {
            throw new DatosInvalidosException("Ya existe un periodo con id "
                    + periodo.getIdPeriodo());
        }
    }

    public PeriodoAcademico buscar(int idPeriodo) {
        PeriodoAcademico periodo = periodos.get(idPeriodo);
        if (periodo == null) {
            throw new RecursoNoEncontradoException("No existe el periodo " + idPeriodo);
        }
        return periodo;
    }

    public List<PeriodoAcademico> listar() {
        return periodos.values().stream()
                .sorted((a, b) -> Integer.compare(a.getIdPeriodo(), b.getIdPeriodo()))
                .toList();
    }

    public void validarMatriculaAbierta(int idPeriodo, LocalDate fecha) {
        Validaciones.noNulo(fecha, "fecha");
        PeriodoAcademico periodo = buscar(idPeriodo);
        if (!periodo.estaAbiertaMatricula(fecha)) {
            throw new PeriodoMatriculaCerradoException("La matricula del periodo "
                    + periodo.getNombre() + " no esta abierta");
        }
    }
}
