
package co.ufps.refupet.security;

import co.ufps.refupet.usuario.IUsuarioRepository;
import co.ufps.refupet.usuario.Usuario;

import io.quarkus.security.identity.SecurityIdentity;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.Map;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class EstadoCuentaFilter
        implements ContainerRequestFilter {

    @Inject
    SecurityIdentity identity;

    @Inject
    IUsuarioRepository usuarioRepository;

    @Override
    public void filter(
            ContainerRequestContext request)
            throws IOException {

        // Las rutas públicas pueden recibir
        // usuarios anónimos. Su acceso se
        // controla con las reglas de seguridad.
        if (identity.isAnonymous()) {
            return;
        }

        String correo = identity
                .getPrincipal()
                .getName();

        Usuario usuario = usuarioRepository
                .findByEmail(correo)
                .orElse(null);

        // Solo las cuentas ACTIVO pueden usar
        // la API una vez autenticadas.
        if (usuario == null || !usuario.estaActivo()) {

            request.abortWith(
                    Response.status(
                                    Response.Status.FORBIDDEN
                            )
                            .entity(Map.of(
                                    "mensaje",
                                    "La cuenta no se encuentra activa"
                            ))
                            .build()
            );
        }
    }
}