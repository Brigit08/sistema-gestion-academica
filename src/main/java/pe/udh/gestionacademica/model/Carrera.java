package pe.udh.gestionacademica.model;

import java.util.Objects;

public class Carrera {

    private final int idCarrera;
    private final String nombre;
    private final int idFacultad;

    public Carrera(int idCarrera, String nombre, int idFacultad) {
        this.idCarrera = idCarrera;
        this.nombre = Objects.requireNonNull(nombre, "El nombre de la carrera es obligatorio");
        this.idFacultad = idFacultad;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdFacultad() {
        return idFacultad;
    }
}
