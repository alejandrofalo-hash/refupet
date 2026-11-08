
package co.ufps.refupet.usuario;

import co.ufps.refupet.DomainException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.NoSuchElementException;

@ApplicationScoped
public class SuspenderCuentaUseCase {

    private final IUsuarioRepository usuarioRepository;

    @Inject
    public SuspenderCuentaUseCase(
            IUsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO ejecutar(
            String usuarioId,
            String correoAdministrador) {

        // 1. Validar el identificador recibido
        if (usuarioId == null || usuarioId.isBlank()) {
            throw new IllegalArgumentException(
                    "El identificador del usuario es obligatorio"
            );
        }

        if (correoAdministrador == null ||
                correoAdministrador.isBlank()) {
            throw new IllegalArgumentException(
                    "Debe identificarse el administrador"
            );
        }

        // 2. Buscar el usuario que se quiere suspender
        Usuario usuario = usuarioRepository
                .findById(new UsuarioId(usuarioId))
                .orElseThrow(() -> new NoSuchElementException(
                        "El usuario no existe"
                ));

        // 3. Impedir que el administrador se suspenda
        if (usuario.getEmail().equalsIgnoreCase(
                correoAdministrador.trim())) {

            throw new DomainException(
                    "No puede suspender su propia cuenta"
            );
        }

        // 4. Aplicar la regla de negocio
        usuario.suspender();

        // 5. Guardar el cambio de estado
        Usuario usuarioActualizado =
                usuarioRepository.guardar(usuario);

        // 6. Devolver el resultado
        return UsuarioResponseDTO.fromDomain(
                usuarioActualizado
        );
    }
}
