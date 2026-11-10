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

    @Inject
    RegistrarVacunaUseCase registrarVacunaUseCase;

    @Inject
    RegistrarDesparasitacionUseCase registrarDesparasitacionUseCase;

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

    @POST
    @Path("/{animalId}/vacunas")
    @RolesAllowed("VETERINARIO")
    public Response registrarVacuna(
            @PathParam("animalId") Long animalId,
            RegistrarVacunaRequest request) {

        RegistrarVacunaCommand command = request.toCommand(animalId);
        VacunaAplicadaResponseDTO response = registrarVacunaUseCase.ejecutar(command);

        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @POST
    @Path("/{animalId}/desparasitaciones")
    @RolesAllowed("VETERINARIO")
    public Response registrarDesparasitacion(
            @PathParam("animalId") Long animalId,
            RegistrarDesparasitacionRequest request) {

        RegistrarDesparasitacionCommand command = request.toCommand(animalId);
        DesparasitacionResponseDTO response = registrarDesparasitacionUseCase.ejecutar(command);

        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }
}