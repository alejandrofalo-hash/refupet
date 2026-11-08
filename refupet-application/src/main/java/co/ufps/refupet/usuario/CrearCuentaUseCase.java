package co.ufps.refupet.usuario;

import co.ufps.refupet.DomainException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CrearCuentaUseCase {

    private final IUsuarioRepository usuarioRepository;

    @Inject
    public CrearCuentaUseCase(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO ejecutar(CrearUsuarioCommand command) {
        // 1. Validar que el identificador/email no esté registrado previamente
        if (usuarioRepository.existsByEmail(command.getEmail())) {
            throw new DomainException("Ya existe una cuenta registrada con el email: " + command.getEmail());
        }

        // 2. Hash de la contraseña (o asignación directa si no usas un encoder separado aún)
        String passwordHash = command.getPassword();

        // 3. Crear entidad de dominio (asigna ID único, rol y estado correspondiente)
        Usuario nuevoUsuario = Usuario.crear(
                command.getNombre(),
                command.getEmail(),
                passwordHash,
                command.getRol()
        );

        // 4. Guardar a través del repositorio
        Usuario usuarioGuardado = usuarioRepository.guardar(nuevoUsuario);

        // 5. Retornar DTO de respuesta
        return UsuarioResponseDTO.fromDomain(usuarioGuardado);
    }
}