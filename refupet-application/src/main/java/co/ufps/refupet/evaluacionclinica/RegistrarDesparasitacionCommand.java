package co.ufps.refupet.evaluacionclinica;

import java.time.LocalDate;

public record RegistrarDesparasitacionCommand(
        Long animalId,
        Long veterinarioId,
        String producto,
        String dosis,
        LocalDate fechaAplicacion
) {}