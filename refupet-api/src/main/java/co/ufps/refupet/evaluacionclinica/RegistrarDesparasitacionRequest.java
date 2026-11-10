package co.ufps.refupet.evaluacionclinica;

import java.time.LocalDate;

public record RegistrarDesparasitacionRequest(
        Long veterinarioId,
        String producto,
        String dosis,
        LocalDate fechaAplicacion
) {
    public RegistrarDesparasitacionCommand toCommand(Long animalId) {
        return new RegistrarDesparasitacionCommand(
                animalId,
                this.veterinarioId,
                this.producto,
                this.dosis,
                this.fechaAplicacion
        );
    }
}