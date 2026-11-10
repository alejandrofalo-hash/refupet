package co.ufps.refupet.evaluacionclinica;

import java.time.LocalDate;

public record RegistrarVacunaRequest(
        Long veterinarioId,
        String nombreVacuna,
        LocalDate fechaAplicacion,
        LocalDate fechaProximaDosis
) {
    public RegistrarVacunaCommand toCommand(Long animalId) {
        return new RegistrarVacunaCommand(
                animalId,
                this.veterinarioId,
                this.nombreVacuna,
                this.fechaAplicacion,
                this.fechaProximaDosis
        );
    }
}