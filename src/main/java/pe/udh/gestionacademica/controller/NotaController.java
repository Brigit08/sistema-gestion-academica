package pe.udh.gestionacademica.controller;

import pe.udh.gestionacademica.model.Nota;
import java.util.ArrayList;
import java.util.List;

public class NotaController {
    private List<Nota> listaNotas;

    public NotaController() {
        this.listaNotas = new ArrayList<>();
    }

    public void registrarNota(Nota nota) {
        listaNotas.add(nota);
    }

    public List<Nota> obtenerNotas() {
        return listaNotas;
    }
}