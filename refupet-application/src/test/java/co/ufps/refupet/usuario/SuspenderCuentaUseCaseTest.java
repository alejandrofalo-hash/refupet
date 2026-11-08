
package co.ufps.refupet.usuario;

import co.ufps.refupet.DomainException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SuspenderCuentaUseCaseTest {

    private IUsuarioRepository repository;
    private SuspenderCuentaUseCase useCase;

    private static final String ADMIN_EMAIL =
            "admin@refupet.com";

    @BeforeEach
    void configurar() {
        repository = mock(IUsuarioRepository.class);
        useCase = new SuspenderCuentaUseCase(repository);
    }

    private Usuario crearUsuario(
            String id,
            String correo,
            EstadoUsuario estado) {

        return Usuario.reordenar(
                new UsuarioId(id),
                "Usuario de prueba",
                correo,
                "hash-de-prueba",
                Rol.VOLUNTARIO,
                estado
        );
    }

    @Test
    void debeSuspenderUsuarioActivo() {

        Usuario usuario = crearUsuario(
                "usuario-1",
                "voluntario@refupet.com",
                EstadoUsuario.ACTIVO
        );

        when(repository.findById(
                new UsuarioId("usuario-1")))
                .thenReturn(Optional.of(usuario));

        when(repository.guardar(any(Usuario.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0));

        UsuarioResponseDTO resultado =
                useCase.ejecutar(
                        "usuario-1",
                        ADMIN_EMAIL
                );

        assertEquals(
                "INACTIVO",
                resultado.getEstadoUsuario()
        );

        assertEquals(
                "voluntario@refupet.com",
                resultado.getEmail()
        );

        verify(repository).guardar(usuario);
    }

    @Test
    void noDebeSuspenderCuentaPendiente() {

        Usuario usuario = crearUsuario(
                "usuario-2",
                "pendiente@refupet.com",
                EstadoUsuario.PENDIENTE
        );

        when(repository.findById(
                new UsuarioId("usuario-2")))
                .thenReturn(Optional.of(usuario));

        assertThrows(
                DomainException.class,
                () -> useCase.ejecutar(
                        "usuario-2",
                        ADMIN_EMAIL
                )
        );

        verify(repository, never())
                .guardar(any(Usuario.class));
    }

    @Test
    void noDebeSuspenderCuentaInactiva() {

        Usuario usuario = crearUsuario(
                "usuario-3",
                "inactivo@refupet.com",
                EstadoUsuario.INACTIVO
        );

        when(repository.findById(
                new UsuarioId("usuario-3")))
                .thenReturn(Optional.of(usuario));

        assertThrows(
                DomainException.class,
                () -> useCase.ejecutar(
                        "usuario-3",
                        ADMIN_EMAIL
                )
        );

        verify(repository, never())
                .guardar(any(Usuario.class));
    }

    @Test
    void noDebeSuspenderSuPropiaCuenta() {

        Usuario usuario = crearUsuario(
                "usuario-4",
                ADMIN_EMAIL,
                EstadoUsuario.ACTIVO
        );

        when(repository.findById(
                new UsuarioId("usuario-4")))
                .thenReturn(Optional.of(usuario));

        assertThrows(
                DomainException.class,
                () -> useCase.ejecutar(
                        "usuario-4",
                        ADMIN_EMAIL
                )
        );

        verify(repository, never())
                .guardar(any(Usuario.class));
    }

    @Test
    void debeRechazarUsuarioInexistente() {

        when(repository.findById(
                new UsuarioId("no-existe")))
                .thenReturn(Optional.empty());

        assertThrows(
                NoSuchElementException.class,
                () -> useCase.ejecutar(
                        "no-existe",
                        ADMIN_EMAIL
                )
        );
    }
}