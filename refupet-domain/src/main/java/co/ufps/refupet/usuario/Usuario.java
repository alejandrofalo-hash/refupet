package co.ufps.refupet.usuario;

import co.ufps.refupet.DomainException;
import java.util.Objects;

public class Usuario {

    private final UsuarioId usuarioId;
    private String nombre;
    private String email;
    private String passwordHash;
    private Rol rol;
    private EstadoUsuario estadoUsuario;

    private Usuario(UsuarioId usuarioId, String nombre, String email, String passwordHash, Rol rol, EstadoUsuario estadoUsuario) {
        this.usuarioId = Objects.requireNonNull(usuarioId, "El identificador único (UsuarioId) es obligatorio");
        this.nombre = validarNombre(nombre);
        this.email = validarEmail(email);
        this.passwordHash = Objects.requireNonNull(passwordHash, "El passwordHash es obligatorio");
        this.rol = Objects.requireNonNull(rol, "Se debe asignar un rol válido");
        this.estadoUsuario = estadoUsuario != null ? estadoUsuario : EstadoUsuario.PENDIENTE;
    }

    // ── Paso 1: Validar Datos ──
    private String validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DomainException("El nombre del usuario es obligatorio y no puede estar vacío");
        }
        return nombre.trim();
    }

    private String validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new DomainException("El email es obligatorio");
        }
        String emailLimpio = email.trim().toLowerCase();
        if (!emailLimpio.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new DomainException("El formato del email no es válido");
        }
        return emailLimpio;
    }

    // ── Pasos 1, 2 y 3: Crear cuenta con ID único, rol y estado correspondiente ──
    public static Usuario crear(String nombre, String email, String passwordHash, Rol rol) {
        EstadoUsuario estadoInicial = (rol == Rol.SOLICITANTE) ? EstadoUsuario.PENDIENTE : EstadoUsuario.ACTIVO;
        return new Usuario(UsuarioId.generate(), nombre, email, passwordHash, rol, estadoInicial);
    }

    public static Usuario reordenar(UsuarioId usuarioId, String nombre, String email, String passwordHash, Rol rol, EstadoUsuario estadoUsuario) {
        return new Usuario(usuarioId, nombre, email, passwordHash, rol, estadoUsuario);
    }

    // ── Paso 2: Asignar / Cambiar Rol ──
    public void cambiarRol(Rol nuevoRol) {
        if (nuevoRol == null) {
            throw new DomainException("El nuevo rol no puede ser nulo o inválido");
        }
        if (this.rol == nuevoRol) {
            throw new DomainException("El usuario ya posee el rol asignado: " + nuevoRol);
        }
        this.rol = nuevoRol;
    }

    // ── Paso 4: Aplicar Permisos según Rol ──
    public boolean tienePermiso(Permiso permiso) {
        if (!estaActivo()) {
            throw new DomainException("La cuenta no se encuentra activa para realizar esta acción");
        }
        return this.rol.tienePermiso(permiso);
    }

    public void validarPermiso(Permiso permiso) {
        if (!tienePermiso(permiso)) {
            throw new DomainException("El usuario con rol " + this.rol + " no tiene permiso para realizar la acción: " + permiso);
        }
    }

    public void activar() {
        this.estadoUsuario = EstadoUsuario.ACTIVO;
    }

    public void desactivar() {
        this.estadoUsuario = EstadoUsuario.INACTIVO;
    }

    public boolean estaActivo() {
        return this.estadoUsuario == EstadoUsuario.ACTIVO;
    }

    // ── Getters ──
    public UsuarioId getUsuarioId() { return usuarioId; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public Rol getRol() { return rol; }
    public EstadoUsuario getEstadoUsuario() { return estadoUsuario; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(usuarioId, usuario.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId);
    }
}