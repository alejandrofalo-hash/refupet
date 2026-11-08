package co.ufps.refupet.persistencia.usuario;

import co.ufps.refupet.usuario.EstadoUsuario;
import co.ufps.refupet.usuario.Rol;
import co.ufps.refupet.usuario.Usuario;
import co.ufps.refupet.usuario.UsuarioId;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuarioJpa {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_usuario", nullable = false)
    private EstadoUsuario estadoUsuario;

    public UsuarioJpa() {
    }

    public UsuarioJpa(String id, String nombre, String email, String passwordHash, Rol rol, EstadoUsuario estadoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.estadoUsuario = estadoUsuario;
    }

    // Convierte Entidad JPA -> Entidad de Dominio
    public Usuario toDomain() {
        return Usuario.reordenar(
                new UsuarioId(this.id),
                this.nombre,
                this.email,
                this.passwordHash,
                this.rol,
                this.estadoUsuario
        );
    }

    // Convierte Entidad de Dominio -> Entidad JPA
    public static UsuarioJpa fromDomain(Usuario usuario) {
        return new UsuarioJpa(
                usuario.getUsuarioId().getValue(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPasswordHash(),
                usuario.getRol(),
                usuario.getEstadoUsuario()
        );
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public EstadoUsuario getEstadoUsuario() { return estadoUsuario; }
    public void setEstadoUsuario(EstadoUsuario estadoUsuario) { this.estadoUsuario = estadoUsuario; }
}