
package co.ufps.refupet.usuario;

import co.ufps.refupet.DomainException;
import co.ufps.refupet.auth.PasswordHasher;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@ApplicationScoped
public class CrearCuentaUseCase {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordHasher passwordHasher;

    @Inject
    public CrearCuentaUseCase(
            IUsuarioRepository usuarioRepository,
            PasswordHasher passwordHasher) {

        this.usuarioRepository = usuarioRepository;
        this.passwordHasher = passwordHasher;
    }

    public UsuarioResponseDTO ejecutar(
            CrearUsuarioCommand command) {

        if (command == null) {
            throw new DomainException(
                    "Los datos son obligatorios"
            );
        }

        if (command.getEmail() == null) {
            throw new DomainException(
                    "El correo es obligatorio"
            );
        }

        if (command.getRol() == null) {
            throw new DomainException(
                    "El rol es obligatorio"
            );
        }

        String email = command.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);

        String password = command.getPassword();

        if (password == null ||
                password.length() < 12 ||
                password.getBytes(
                        StandardCharsets.UTF_8
                ).length > 72) {

            throw new DomainException(
                    "La contraseña debe tener mínimo " +
                            "12 caracteres y máximo 72 bytes"
            );
        }

        if (usuarioRepository.existsByEmail(email)) {
            throw new DomainException(
                    "Ya existe una cuenta con ese correo"
            );
        }

        String hash = passwordHasher.hash(password);

        Usuario usuario = Usuario.crear(
                command.getNombre(),
                email,
                hash,
                command.getRol()
        );

        return UsuarioResponseDTO.fromDomain(
                usuarioRepository.guardar(usuario)
        );
    }
}
