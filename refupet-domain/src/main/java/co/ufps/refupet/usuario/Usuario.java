package co.ufps.refupet.usuario;

import co.ufps.refupet.DomainException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {

    private UsuarioId usuarioId;
    private String nombre;
    private String email;
    private String passwordHash;
    private Rol rol;
    private EstadoUsuario estadoUsuario;
    private LocalDateTime fechaCreacion;

    private Usuario(UsuarioId usuarioId, String nombre, String email, String passwordHash, Rol rol, EstadoUsuario estadoUsuario) {
        this.usuarioId = Objects.requireNonNull(usuarioId, "usuarioId es obligatorio");
        this.nombre = Objects.requireNonNull(nombre, "El nombre es obligatorio");
        this.email = Objects.requireNonNull(email, "El email es obligatorio");
        this.passwordHash = Objects.requireNonNull(passwordHash, "passwordHash es obligatorio");
        this.rol = Objects.requireNonNull(rol, "El rol es obligatorio");
        this.estadoUsuario = estadoUsuario != null ? estadoUsuario : EstadoUsuario.ACTIVO;
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Factory Method para el registro/creación de cuentas según HU-15.
     * Aplica las reglas e invariantes del dominio.
     */
    public static Usuario registrar(String nombre, String email, String passwordHash, Rol rol) {
        if (nombre == null || nombre.isBlank()) {
            throw new DomainException("El nombre del usuario es obligatorio");
        }
        if (email == null || email.isBlank()) {
            throw new DomainException("El email es obligatorio");
        }
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new DomainException("La contraseña es obligatoria");
        }
        if (rol == null) {
            throw new DomainException("El rol assigned es obligatorio para la cuenta");
        }

        return new Usuario(UsuarioId.generate(), nombre, email, passwordHash, rol, EstadoUsuario.ACTIVO);
    }

    /**
     * Factory Method para la reconstrucción desde la capa de infraestructura (JPA/DB).
     */
    public static Usuario reordenar(UsuarioId usuarioId, String nombre, String email, String passwordHash,
                                    Rol rol, EstadoUsuario estadoUsuario, LocalDateTime fechaCreacion) {
        Usuario usuario = new Usuario(usuarioId, nombre, email, passwordHash, rol, estadoUsuario);
        usuario.fechaCreacion = fechaCreacion;
        return usuario;
    }

    // ── Comandos de Negocio ──

    public void cambiarRol(Rol nuevoRol) {
        if (nuevoRol == null) {
            throw new DomainException("El nuevo rol no puede ser nulo");
        }
        if (this.rol == nuevoRol) {
            throw new DomainException("El usuario ya posee el rol asignado: " + nuevoRol);
        }
        this.rol = nuevoRol;
    }

    public void desactivar() {
        this.estadoUsuario = EstadoUsuario.INACTIVO;
    }

    public void activar() {
        this.estadoUsuario = EstadoUsuario.ACTIVO;
    }

    // ── Getters (Inmutables) ──

    public UsuarioId getUsuarioId() { return usuarioId; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public Rol getRol() { return rol; }
    public EstadoUsuario getEstadoUsuario() { return estadoUsuario; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
}
