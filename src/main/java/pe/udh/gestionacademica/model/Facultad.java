package pe.udh.gestionacademica.model;

import java.util.Objects;

public class Facultad {

    private final int idFacultad;
    private final String nombre;

    public Facultad(int idFacultad, String nombre) {
        this.idFacultad = idFacultad;
        this.nombre = Objects.requireNonNull(nombre, "El nombre de la facultad es obligatorio");
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public String getNombre() {
        return nombre;
    }
}
