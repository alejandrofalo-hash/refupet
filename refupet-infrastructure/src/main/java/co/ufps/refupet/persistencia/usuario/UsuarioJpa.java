
package co.ufps.refupet.persistencia.usuario;

import co.ufps.refupet.usuario.EstadoUsuario;
import co.ufps.refupet.usuario.Rol;
import co.ufps.refupet.usuario.Usuario;
import co.ufps.refupet.usuario.UsuarioId;

import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@UserDefinition
public class UsuarioJpa {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Username
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Password
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Roles
    @Column(name = "rol", nullable = false)
    private String rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_usuario", nullable = false)
    private EstadoUsuario estadoUsuario;

    public UsuarioJpa() {
    }

    public UsuarioJpa(
            String id,
            String nombre,
            String email,
            String passwordHash,
            String rol,
            EstadoUsuario estadoUsuario) {

        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.estadoUsuario = estadoUsuario;
    }

    public Usuario toDomain() {
        return Usuario.reordenar(
                new UsuarioId(id),
                nombre,
                email,
                passwordHash,
                Rol.valueOf(rol),
                estadoUsuario
        );
    }

    public static UsuarioJpa fromDomain(Usuario usuario) {
        return new UsuarioJpa(
                usuario.getUsuarioId().getValue(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPasswordHash(),
                usuario.getRol().name(),
                usuario.getEstadoUsuario()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(
            EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }
}
