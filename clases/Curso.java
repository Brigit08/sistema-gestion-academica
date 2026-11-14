public class Curso {

    private int idCurso;
    private String codigo;
    private String nombre;
    private int creditos;
    private int ciclo;
    private int idCarrera;

    public Curso(int idCurso, String codigo, String nombre,
                 int creditos, int ciclo, int idCarrera) {
        this.idCurso = idCurso;
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.ciclo = ciclo;
        this.idCarrera = idCarrera;
    }

    public int getIdCurso() { return idCurso; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCreditos() { return creditos; }
    public int getCiclo() { return ciclo; }
    public int getIdCarrera() { return idCarrera; }
}