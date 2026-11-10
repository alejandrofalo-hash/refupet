package co.ufps.refupet.evaluacionclinica;

import co.ufps.refupet.evaluacionclinica.model.EstadoNutricional;
import co.ufps.refupet.evaluacionclinica.model.EvaluacionClinica;
import co.ufps.refupet.evaluacionclinica.model.Temperamento;
import java.time.LocalDateTime;

public record EvaluacionClinicaResponseDTO(
        Long id,
        Long animalId,
        Long veterinarioId,
        EstadoNutricional estadoNutricional,
        String heridas,
        String parasitos,
        Temperamento temperamento,
        LocalDateTime fechaRegistro
) {
    public static EvaluacionClinicaResponseDTO fromDomain(EvaluacionClinica evaluacion) {
        return new EvaluacionClinicaResponseDTO(
                evaluacion.getId(),
                evaluacion.getAnimalId(),
                evaluacion.getVeterinarioId(),
                evaluacion.getEstadoNutricional(),
                evaluacion.getHeridas(),
                evaluacion.getParasitos(),
                evaluacion.getTemperamento(),
                evaluacion.getFechaRegistro()
        );
    }
}