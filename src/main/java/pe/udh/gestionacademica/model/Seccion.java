package pe.udh.gestionacademica.model;

public class Seccion {

    private final int id;
    private final Curso curso;
    private final int cupo;
    private int matriculados;

    public Seccion(int id, Curso curso, int cupo) {
        if (cupo <= 0) {
            throw new IllegalArgumentException("El cupo debe ser mayor que cero");
        }
        this.id = id;
        this.curso = curso;
        this.cupo = cupo;
    }

    public boolean hayCupo() {
        return matriculados < cupo;
    }

    public void ocuparCupo() {
        matriculados++;
    }

    public int getId() {
        return id;
    }

    public Curso getCurso() {
        return curso;
    }

    public int getCupo() {
        return cupo;
    }

    public int getCupoDisponible() {
        return cupo - matriculados;
    }
}
