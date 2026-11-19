package pe.udh.gestionacademica.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import pe.udh.gestionacademica.exception.NotaException;
import pe.udh.gestionacademica.exception.RecursoNoEncontradoException;
import pe.udh.gestionacademica.model.DetalleMatricula;
import pe.udh.gestionacademica.model.HistorialNota;
import pe.udh.gestionacademica.model.Nota;
import pe.udh.gestionacademica.util.GeneradorId;
import pe.udh.gestionacademica.util.Validaciones;

public class NotaService {

    private final MatriculaService matriculas;
    private final AuditoriaNotas auditoria;
    private final Map<Integer, Nota> notas;
    private final GeneradorId ids;

    public NotaService(MatriculaService matriculas, AuditoriaNotas auditoria) {
        Validaciones.noNulo(matriculas, "matriculas");
        Validaciones.noNulo(auditoria, "auditoria");
        this.matriculas = matriculas;
        this.auditoria = auditoria;
        this.notas = new ConcurrentHashMap<>();
        this.ids = new GeneradorId(1);
    }

    public Nota registrar(int idDetalle, String tipoEvaluacion, double valor,
                          double peso, int idUsuario) {
        DetalleMatricula detalle = matriculas.buscarDetalle(idDetalle);
        if (!detalle.estaActivo()) {
            throw new NotaException("No se pueden registrar notas en un detalle retirado");
        }
        String tipo = Validaciones.requerido(tipoEvaluacion, "tipoEvaluacion");
        Validaciones.rango(valor, Nota.NOTA_MINIMA, Nota.NOTA_MAXIMA, "valor");
        Validaciones.rango(peso, 0.01, 100, "peso");
        Validaciones.enteroPositivo(idUsuario, "idUsuario");
        Nota nota = new Nota(ids.siguiente(), idDetalle, tipo, valor, peso);
        notas.put(nota.getIdNota(), nota);
        auditoria.registrar(nota.getIdNota(), 0.0, valor, idUsuario,
                "Registro inicial de la nota", LocalDateTime.now());
        return nota;
    }

    public Nota actualizar(int idNota, double nuevoValor, int idUsuario, String motivo) {
        Nota nota = buscar(idNota);
        Validaciones.rango(nuevoValor, Nota.NOTA_MINIMA, Nota.NOTA_MAXIMA, "nuevoValor");
        Validaciones.enteroPositivo(idUsuario, "idUsuario");
        String motivoValidado = Validaciones.requerido(motivo, "motivo");
        double valorAnterior = nota.getValor();
        nota.actualizarValor(nuevoValor);
        auditoria.registrar(idNota, valorAnterior, nuevoValor, idUsuario,
                motivoValidado, LocalDateTime.now());
        return nota;
    }

    public Nota buscar(int idNota) {
        Nota nota = notas.get(idNota);
        if (nota == null) {
            throw new RecursoNoEncontradoException("No existe la nota " + idNota);
        }
        return nota;
    }

    public List<Nota> listarPorDetalle(int idDetalle) {
        return notas.values().stream()
                .filter(nota -> nota.getIdDetalle() == idDetalle)
                .sorted(Comparator.comparingInt(Nota::getIdNota))
                .toList();
    }

    public double promedioPonderado(int idDetalle) {
        List<Nota> notasDetalle = listarPorDetalle(idDetalle);
        double sumaPonderada = 0;
        double sumaPesos = 0;
        for (Nota nota : notasDetalle) {
            sumaPonderada += nota.getValor() * nota.getPeso();
            sumaPesos += nota.getPeso();
        }
        if (sumaPesos == 0) {
            return 0;
        }
        return Math.round((sumaPonderada / sumaPesos) * 100.0) / 100.0;
    }

    public boolean aprobo(int idDetalle, double notaMinima) {
        return promedioPonderado(idDetalle) >= notaMinima;
    }

    public List<HistorialNota> historial(int idNota) {
        return auditoria.listarPorNota(idNota);
    }

    public List<HistorialNota> historialCompleto() {
        return auditoria.listar();
    }
}
