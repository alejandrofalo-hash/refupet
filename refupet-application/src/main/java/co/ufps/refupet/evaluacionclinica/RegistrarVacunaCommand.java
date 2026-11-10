package co.ufps.refupet.evaluacionclinica;

import java.time.LocalDate;

public record RegistrarVacunaCommand(
        Long animalId,
        Long veterinarioId,
        String nombreVacuna,
        LocalDate fechaAplicacion,
        LocalDate fechaProximaDosis
) {}