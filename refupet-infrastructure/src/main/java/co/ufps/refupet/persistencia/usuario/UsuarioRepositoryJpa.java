package co.ufps.refupet.persistencia.usuario;

import co.ufps.refupet.usuario.IUsuarioRepository;
import co.ufps.refupet.usuario.Usuario;
import co.ufps.refupet.usuario.UsuarioId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import co.ufps.refupet.usuario.EstadoUsuario;

/**
 * Adaptador del repositorio de usuarios usando JPA/Hibernate.
 * Mapea entre la entidad de dominio (Usuario) y la entidad JPA (UsuarioJpa).
 */
@ApplicationScoped
public class UsuarioRepositoryJpa implements IUsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        UsuarioJpa usuarioJpa = toEntity(usuario);
        entityManager.merge(usuarioJpa);
        return usuario;
    }

    @Override
    public boolean existsByEmail(String email) {
        Long count = entityManager
                .createQuery("SELECT COUNT(u) FROM UsuarioJpa u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public Optional<Usuario> findById(UsuarioId id) {
        UsuarioJpa usuarioJpa = entityManager
                .createQuery("SELECT u FROM UsuarioJpa u WHERE u.id = :id", UsuarioJpa.class)
                .setParameter("id", id.getValue())
                .getResultList().stream().findFirst().orElse(null);

        if (usuarioJpa == null) {
            return Optional.empty();
        }

        return Optional.of(toDomain(usuarioJpa));
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        UsuarioJpa usuarioJpa = entityManager
                .createQuery("SELECT u FROM UsuarioJpa u WHERE u.email = :email", UsuarioJpa.class)
                .setParameter("email", email)
                .getResultList().stream().findFirst().orElse(null);

        if (usuarioJpa == null) {
            return Optional.empty();
        }

        return Optional.of(toDomain(usuarioJpa));
    }

    // ── Mapeo: Domain → JPA ──────────────────────────────────────────

    private UsuarioJpa toEntity(Usuario usuario) {
        return UsuarioJpa.fromDomain(usuario);
    }

    // ── Mapeo: JPA → Domain ──────────────────────────────────────────

    private Usuario toDomain(UsuarioJpa usuarioJpa) {
        return usuarioJpa.toDomain();
    }

    @Override
    public List<Usuario> findPendientes() {

        return entityManager.createQuery(
                        "SELECT u FROM UsuarioJpa u " +
                                "WHERE u.estadoUsuario = :estado " +
                                "ORDER BY u.nombre ASC",
                        UsuarioJpa.class
                )
                .setParameter(
                        "estado", EstadoUsuario.PENDIENTE
                )
                .getResultStream()
                .map(UsuarioJpa::toDomain)
                .toList();
    }

}
