package co.ufps.refupet.evaluacionclinica;

import co.ufps.refupet.evaluacionclinica.model.EstadoNutricional;
import co.ufps.refupet.evaluacionclinica.model.Temperamento;

public record RegistrarEvaluacionClinicaRequest(
        Long veterinarioId,
        EstadoNutricional estadoNutricional,
        String heridas,
        String parasitos,
        Temperamento temperamento
) {
    public RegistrarEvaluacionClinicaCommand toCommand(Long animalId) {
        return new RegistrarEvaluacionClinicaCommand(
                animalId,
                this.veterinarioId,
                this.estadoNutricional,
                this.heridas,
                this.parasitos,
                this.temperamento
        );
    }
}