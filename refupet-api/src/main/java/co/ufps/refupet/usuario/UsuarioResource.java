
package co.ufps.refupet.usuario;

import co.ufps.refupet.DomainException;
import co.ufps.refupet.security.AdministradorActivoGuard;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMINISTRADOR")
public class UsuarioResource {

    @Inject
    CrearCuentaUseCase crearCuentaUseCase;

    @Inject
    AprobarCuentaUseCase aprobarCuentaUseCase;

    @Inject
    ListarCuentasPendientesUseCase listarPendientesUseCase;

    @Inject
    AdministradorActivoGuard administradorGuard;

    @Inject
    SuspenderCuentaUseCase suspenderCuentaUseCase;

    @Inject
    SecurityIdentity identity;

    @POST
    public Response crear(CrearUsuarioRequest request) {

        administradorGuard.verificar();

        if (request == null) {
            return Response.status(400)
                    .entity(Map.of(
                            "mensaje", "Los datos son obligatorios"
                    ))
                    .build();
        }

        try {
            UsuarioResponseDTO resultado =
                    crearCuentaUseCase.ejecutar(
                            request.toCommand()
                    );

            return Response.created(
                            URI.create(
                                    "/api/usuarios/" + resultado.getId()
                            )
                    )
                    .entity(resultado)
                    .build();

        } catch (DomainException |
                 IllegalArgumentException e) {

            return Response.status(400)
                    .entity(Map.of("mensaje", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/pendientes")
    public List<UsuarioResponseDTO> listarPendientes() {

        administradorGuard.verificar();

        return listarPendientesUseCase.ejecutar();
    }

    @PUT
    @Path("/{id}/aprobar")
    public Response aprobar(@PathParam("id") String id) {

        administradorGuard.verificar();

        try {
            UsuarioResponseDTO resultado =
                    aprobarCuentaUseCase.ejecutar(id);

            return Response.ok(resultado).build();

        } catch (DomainException |
                 IllegalArgumentException e) {

            return Response.status(400)
                    .entity(Map.of("mensaje", e.getMessage()))
                    .build();
        }
    }

    @PUT
    @Path("/{id}/suspender")
    public Response suspender(
            @PathParam("id") String id) {

        // Verificar que sea un administrador ACTIVO
        administradorGuard.verificar();

        try {
            UsuarioResponseDTO resultado =
                    suspenderCuentaUseCase.ejecutar(
                            id,
                            identity.getPrincipal().getName()
                    );

            return Response.ok(resultado).build();

        } catch (NoSuchElementException e) {

            return Response.status(
                            Response.Status.NOT_FOUND
                    )
                    .entity(Map.of(
                            "mensaje", e.getMessage()
                    ))
                    .build();

        } catch (DomainException e) {

            return Response.status(
                            Response.Status.CONFLICT
                    )
                    .entity(Map.of(
                            "mensaje", e.getMessage()
                    ))
                    .build();

        } catch (IllegalArgumentException e) {

            return Response.status(
                            Response.Status.BAD_REQUEST
                    )
                    .entity(Map.of(
                            "mensaje", e.getMessage()
                    ))
                    .build();
        }
    }
}
