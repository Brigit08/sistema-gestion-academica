package clases;

public class Usuario {

    private int idUsuario;
    private String username;
    private String passwordHash;
    private String rol;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(int idUsuario, String username, String passwordHash, String rol, boolean activo) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

/**
 * Usuario del sistema (credenciales y rol).
 * Atributos:
 *   - idUsuario: int
 *   - username: String
 *   - passwordHash: String
 *   - rol: String
 *   - activo: boolean
 * Hito 1: solo estructura, sin código.
 */
