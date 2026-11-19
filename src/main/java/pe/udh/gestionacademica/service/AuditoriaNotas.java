package pe.udh.gestionacademica.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import pe.udh.gestionacademica.model.HistorialNota;
import pe.udh.gestionacademica.util.GeneradorId;
import pe.udh.gestionacademica.util.Validaciones;

public class AuditoriaNotas {

    private final Queue<HistorialNota> registros;
    private final GeneradorId ids;

    public AuditoriaNotas() {
        this(new GeneradorId(1));
    }

    public AuditoriaNotas(GeneradorId ids) {
        Validaciones.noNulo(ids, "ids");
        this.registros = new ConcurrentLinkedQueue<>();
        this.ids = ids;
    }

    public void registrar(int idNota, double valorAnterior, double valorNuevo,
                          int idUsuario, String motivo, LocalDateTime fechaCambio) {
        Validaciones.enteroPositivo(idNota, "idNota");
        Validaciones.enteroPositivo(idUsuario, "idUsuario");
        Validaciones.noNulo(fechaCambio, "fechaCambio");
        String motivoValidado = Validaciones.requerido(motivo, "motivo");
        registros.add(new HistorialNota(ids.siguiente(), idNota, valorAnterior,
                valorNuevo, idUsuario, fechaCambio, motivoValidado));
    }

    public List<HistorialNota> listar() {
        return registros.stream()
                .sorted(Comparator.comparingInt(HistorialNota::getIdHistorial))
                .toList();
    }

    public List<HistorialNota> listarPorNota(int idNota) {
        return registros.stream()
                .filter(registro -> registro.getIdNota() == idNota)
                .sorted(Comparator.comparingInt(HistorialNota::getIdHistorial))
                .toList();
    }
}
