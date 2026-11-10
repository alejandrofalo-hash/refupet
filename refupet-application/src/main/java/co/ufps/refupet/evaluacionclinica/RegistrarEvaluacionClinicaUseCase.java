package co.ufps.refupet.evaluacionclinica;

import co.ufps.refupet.animal.IAnimalRepository;
import co.ufps.refupet.DomainException;
import co.ufps.refupet.evaluacionclinica.model.EvaluacionClinica;
import co.ufps.refupet.evaluacionclinica.repository.EvaluacionClinicaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RegistrarEvaluacionClinicaUseCase {

    private final EvaluacionClinicaRepository evaluacionClinicaRepository;
    private final IAnimalRepository animalRepository;

    @Inject
    public RegistrarEvaluacionClinicaUseCase(
            EvaluacionClinicaRepository evaluacionClinicaRepository,
            IAnimalRepository animalRepository) {
        this.evaluacionClinicaRepository = evaluacionClinicaRepository;
        this.animalRepository = animalRepository;
    }

    public EvaluacionClinicaResponseDTO ejecutar(RegistrarEvaluacionClinicaCommand command) {
        // 1. Validar existencia del animal
        animalRepository.buscarPorId(command.animalId())
                .orElseThrow(() -> new DomainException("El animal con ID " + command.animalId() + " no existe."));

        // 2. Crear entidad de dominio
        EvaluacionClinica nuevaEvaluacion = new EvaluacionClinica(
                null,
                command.animalId(),
                command.veterinarioId(),
                command.estadoNutricional(),
                command.heridas(),
                command.parasitos(),
                command.temperamento(),
                null
        );

        // 3. Guardar en repositorio
        EvaluacionClinica evaluacionGuardada = evaluacionClinicaRepository.guardar(nuevaEvaluacion);

        // 4. Retornar DTO de respuesta
        return EvaluacionClinicaResponseDTO.fromDomain(evaluacionGuardada);
    }
}