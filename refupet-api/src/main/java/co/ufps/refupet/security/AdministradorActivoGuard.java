
package co.ufps.refupet.security;

import co.ufps.refupet.usuario.IUsuarioRepository;
import co.ufps.refupet.usuario.Rol;
import co.ufps.refupet.usuario.Usuario;

import io.quarkus.security.identity.SecurityIdentity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ForbiddenException;

@ApplicationScoped
public class AdministradorActivoGuard {

    @Inject
    SecurityIdentity identity;

    @Inject
    IUsuarioRepository usuarioRepository;

    public void verificar() {

        if (identity.isAnonymous()) {
            throw new ForbiddenException(
                    "Se requiere autenticación"
            );
        }

        Usuario administrador = usuarioRepository
                .findByEmail(
                        identity.getPrincipal().getName()
                )
                .orElseThrow(() -> new ForbiddenException(
                        "El usuario no existe"
                ));

        if (!administrador.estaActivo()
                || administrador.getRol() != Rol.ADMINISTRADOR) {

            throw new ForbiddenException(
                    "Solo un administrador activo " +
                            "puede realizar esta operación"
            );
        }
    }
}
