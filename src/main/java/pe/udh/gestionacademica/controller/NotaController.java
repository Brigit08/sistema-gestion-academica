package pe.udh.gestionacademica.controller;

import java.util.List;
import pe.udh.gestionacademica.model.HistorialNota;
import pe.udh.gestionacademica.model.Nota;
import pe.udh.gestionacademica.service.NotaService;

public class NotaController {

    private final NotaService notas;

    public NotaController(NotaService notas) {
        this.notas = notas;
    }

    public Nota registrar(int idDetalle, String tipoEvaluacion, double valor,
                          double peso, int idUsuario) {
        return notas.registrar(idDetalle, tipoEvaluacion, valor, peso, idUsuario);
    }

    public Nota actualizar(int idNota, double nuevoValor, int idUsuario, String motivo) {
        return notas.actualizar(idNota, nuevoValor, idUsuario, motivo);
    }

    public double promedio(int idDetalle) {
        return notas.promedioPonderado(idDetalle);
    }

    public List<Nota> listarPorDetalle(int idDetalle) {
        return notas.listarPorDetalle(idDetalle);
    }

    public List<HistorialNota> historial(int idNota) {
        return notas.historial(idNota);
    }
}
