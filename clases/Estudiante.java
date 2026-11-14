public class Estudiante {

    private String codigo;
    private String nombres;
    private String apellidos;
    private String dni;
    private String correo;
    private int idCarrera;
    private String estado;

    public Estudiante(String codigo, String nombres, String apellidos,
                      String dni, String correo, int idCarrera, String estado) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.correo = correo;
        this.idCarrera = idCarrera;
        this.estado = estado;
    }

    public String getCodigo() { return codigo; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDni() { return dni; }
    public String getCorreo() { return correo; }
    public int getIdCarrera() { return idCarrera; }
    public String getEstado() { return estado; }
}