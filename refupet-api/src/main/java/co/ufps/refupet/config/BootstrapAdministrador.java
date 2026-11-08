
package co.ufps.refupet.config;

import co.ufps.refupet.usuario.*;

import io.quarkus.runtime.StartupEvent;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class BootstrapAdministrador {

    @Inject
    CrearCuentaUseCase crearCuentaUseCase;

    @Inject
    IUsuarioRepository usuarioRepository;

    void inicializar(@Observes StartupEvent evento) {

        String nombre = System.getenv(
                "REFUPET_ADMIN_NOMBRE"
        );

        String correo = System.getenv(
                "REFUPET_ADMIN_EMAIL"
        );

        String password = System.getenv(
                "REFUPET_ADMIN_PASSWORD"
        );

        // Sin configuración explícita, no hace nada.
        if (nombre == null ||
                correo == null ||
                password == null) {
            return;
        }

        // No modificar cuentas ya existentes.
        if (usuarioRepository.existsByEmail(correo)) {
            return;
        }

        UsuarioResponseDTO resultado =
                crearCuentaUseCase.ejecutar(
                        new CrearUsuarioCommand(
                                nombre,
                                correo,
                                password,
                                Rol.ADMINISTRADOR
                        )
                );

        Usuario administrador = usuarioRepository
                .findById(new UsuarioId(resultado.getId()))
                .orElseThrow();

        // Excepción exclusiva de instalación inicial.
        administrador.aprobar();

        usuarioRepository.guardar(administrador);
    }
}
