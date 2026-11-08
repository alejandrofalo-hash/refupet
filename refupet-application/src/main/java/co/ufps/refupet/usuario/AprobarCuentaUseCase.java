
package co.ufps.refupet.usuario;

import co.ufps.refupet.DomainException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AprobarCuentaUseCase {

    private final IUsuarioRepository usuarioRepository;

    @Inject
    public AprobarCuentaUseCase(
            IUsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO ejecutar(String usuarioId) {

        if (usuarioId == null || usuarioId.isBlank()) {
            throw new DomainException(
                    "El identificador del usuario es obligatorio"
            );
        }

        Usuario usuario = usuarioRepository
                .findById(new UsuarioId(usuarioId))
                .orElseThrow(() -> new DomainException(
                        "El usuario no existe"
                ));

        // Regla de negocio definida en Domain
        usuario.aprobar();

        // Guardar el cambio de estado
        Usuario usuarioActualizado =
                usuarioRepository.guardar(usuario);

        return UsuarioResponseDTO.fromDomain(
                usuarioActualizado
        );
    }
}