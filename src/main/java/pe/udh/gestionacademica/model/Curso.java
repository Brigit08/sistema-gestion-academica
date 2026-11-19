package pe.udh.gestionacademica.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Curso {

    private final int idCurso;
    private final String codigo;
    private final String nombre;
    private final int creditos;
    private final int ciclo;
    private final int idCarrera;
    private final List<Prerrequisito> prerrequisitos;

    public Curso(int idCurso, String codigo, String nombre,
                 int creditos, int ciclo, int idCarrera) {
        this.idCurso = idCurso;
        this.codigo = Objects.requireNonNull(codigo, "El codigo del curso es obligatorio");
        this.nombre = Objects.requireNonNull(nombre, "El nombre del curso es obligatorio");
        this.creditos = creditos;
        this.ciclo = ciclo;
        this.idCarrera = idCarrera;
        this.prerrequisitos = new ArrayList<>();
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getCiclo() {
        return ciclo;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void agregarPrerrequisito(int idCursoRequisito) {
        prerrequisitos.add(new Prerrequisito(idCurso, idCursoRequisito));
    }

    public List<Prerrequisito> getPrerrequisitos() {
        return Collections.unmodifiableList(prerrequisitos);
    }

    public boolean tienePrerrequisitos() {
        return !prerrequisitos.isEmpty();
    }
}
