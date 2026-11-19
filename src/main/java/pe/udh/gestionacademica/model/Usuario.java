package pe.udh.gestionacademica.model;

import java.util.Objects;

public class Usuario {

    private final int idUsuario;
    private final String username;
    private final String passwordHash;
    private final Rol rol;
    private boolean activo;

    public Usuario(int idUsuario, String username, String passwordHash,
                   Rol rol, boolean activo) {
        this.idUsuario = idUsuario;
        this.username = Objects.requireNonNull(username, "El username es obligatorio");
        this.passwordHash = Objects.requireNonNull(passwordHash, "El passwordHash es obligatorio");
        this.rol = Objects.requireNonNull(rol, "El rol es obligatorio");
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Rol getRol() {
        return rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
