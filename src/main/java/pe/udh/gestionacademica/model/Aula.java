package pe.udh.gestionacademica.model;

import java.util.Objects;

public class Aula {

    private final int idAula;
    private final String nombre;
    private final int capacidad;

    public Aula(int idAula, String nombre, int capacidad) {
        this.idAula = idAula;
        this.nombre = Objects.requireNonNull(nombre, "El nombre del aula es obligatorio");
        this.capacidad = capacidad;
    }

    public int getIdAula() {
        return idAula;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }
}
