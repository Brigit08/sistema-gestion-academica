package pe.udh.gestionacademica.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Estudiante {

    private final String codigo;
    private final String nombres;
    private final String apellidos;
    private final String dni;
    private final String correo;
    private final int idCarrera;
    private String estado;
    private final Set<Integer> cursosAprobados;

    public Estudiante(String codigo, String nombres, String apellidos,
                      String dni, String correo, int idCarrera, String estado) {
        this.codigo = Objects.requireNonNull(codigo, "El codigo del estudiante es obligatorio");
        this.nombres = Objects.requireNonNull(nombres, "Los nombres son obligatorios");
        this.apellidos = Objects.requireNonNull(apellidos, "Los apellidos son obligatorios");
        this.dni = Objects.requireNonNull(dni, "El DNI es obligatorio");
        this.correo = Objects.requireNonNull(correo, "El correo es obligatorio");
        this.idCarrera = idCarrera;
        this.estado = Objects.requireNonNull(estado, "El estado es obligatorio");
        this.cursosAprobados = new HashSet<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getCorreo() {
        return correo;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = Objects.requireNonNull(estado, "El estado es obligatorio");
    }

    public void aprobarCurso(int idCurso) {
        cursosAprobados.add(idCurso);
    }

    public boolean haAprobado(int idCurso) {
        return cursosAprobados.contains(idCurso);
    }

    public Set<Integer> getCursosAprobados() {
        return Collections.unmodifiableSet(cursosAprobados);
    }

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}
