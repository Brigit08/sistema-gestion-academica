package pe.udh.gestionacademica.model;

import java.util.Objects;

public class Docente {

    private final int idDocente;
    private final String nombres;
    private final String apellidos;
    private final String correo;
    private final String especialidad;

    public Docente(int idDocente, String nombres, String apellidos,
                   String correo, String especialidad) {
        this.idDocente = idDocente;
        this.nombres = Objects.requireNonNull(nombres, "Los nombres son obligatorios");
        this.apellidos = Objects.requireNonNull(apellidos, "Los apellidos son obligatorios");
        this.correo = Objects.requireNonNull(correo, "El correo es obligatorio");
        this.especialidad = Objects.requireNonNull(especialidad, "La especialidad es obligatoria");
    }

    public int getIdDocente() {
        return idDocente;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
