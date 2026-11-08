package co.ufps.refupet.usuario;

import java.util.Optional;

public interface IUsuarioRepository {

    Usuario guardar(Usuario usuario);

    boolean existsByEmail(String email);

    Optional<Usuario> findById(UsuarioId id);

    Optional<Usuario> findByEmail(String email);
}
