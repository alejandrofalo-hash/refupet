package co.ufps.refupet.evaluacionclinica;

import co.ufps.refupet.evaluacionclinica.model.EstadoNutricional;
import co.ufps.refupet.evaluacionclinica.model.Temperamento;

public record RegistrarEvaluacionClinicaCommand(
        Long animalId,
        Long veterinarioId,
        EstadoNutricional estadoNutricional,
        String heridas,
        String parasitos,
        Temperamento temperamento
) {}