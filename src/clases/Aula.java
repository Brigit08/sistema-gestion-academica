package clases;

public class Aula {

    private int idAula;
    private String nombres;
    private int capacidad;

    public Aula() {
    }

    public Aula(int idAula, String nombres, int capacidad) {
        this.idAula = idAula;
        this.nombres = nombres;
        this.capacidad = capacidad;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}

