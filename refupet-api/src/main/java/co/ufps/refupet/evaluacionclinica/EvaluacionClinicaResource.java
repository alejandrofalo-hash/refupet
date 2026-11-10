package co.ufps.refupet.evaluacionclinica;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/animales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EvaluacionClinicaResource {

    @Inject
    RegistrarEvaluacionClinicaUseCase registrarEvaluacionClinicaUseCase;

    @POST
    @Path("/{animalId}/evaluacion-clinica")
    @RolesAllowed("VETERINARIO") // Garantiza que solo el personal veterinario pueda registrar evaluaciones
    public Response registrarEvaluacion(
            @PathParam("animalId") Long animalId,
            RegistrarEvaluacionClinicaRequest request) {

        RegistrarEvaluacionClinicaCommand command = request.toCommand(animalId);
        EvaluacionClinicaResponseDTO response = registrarEvaluacionClinicaUseCase.ejecutar(command);

        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }
}