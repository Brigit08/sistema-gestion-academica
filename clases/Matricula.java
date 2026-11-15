
import java.util.Date;

public class Matricula {

    private int idMatricula;
    private String codigoEstudiante;
    private int idPeriodo;
    private Date fecha;
    private String estado;
    private int totalCreditos;

    public Matricula(int idMatricula, String codigoEstudiante,
                     int idPeriodo, Date fecha, String estado,
                     int totalCreditos) {

        this.idMatricula = idMatricula;
        this.codigoEstudiante = codigoEstudiante;
        this.idPeriodo = idPeriodo;
        this.fecha = fecha;
        this.estado = estado;
        this.totalCreditos = totalCreditos;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public int getTotalCreditos() {
        return totalCreditos;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTotalCreditos(int totalCreditos) {
        this.totalCreditos = totalCreditos;
    }
}
