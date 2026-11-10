package co.ufps.refupet.evaluacionclinica.repository;

import co.ufps.refupet.evaluacionclinica.model.EvaluacionClinica;
import java.util.Optional;

public interface EvaluacionClinicaRepository {
    EvaluacionClinica guardar(EvaluacionClinica evaluacion);
    Optional<EvaluacionClinica> buscarPorAnimalId(Long animalId);
}