package co.ufps.refupet.evaluacionclinica;

import co.ufps.refupet.animal.IAnimalRepository;
import co.ufps.refupet.DomainException;
import co.ufps.refupet.evaluacionclinica.model.VacunaAplicada;
import co.ufps.refupet.evaluacionclinica.repository.VacunaAplicadaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RegistrarVacunaUseCase {

    private final VacunaAplicadaRepository vacunaAplicadaRepository;
    private final IAnimalRepository animalRepository;

    @Inject
    public RegistrarVacunaUseCase(
            VacunaAplicadaRepository vacunaAplicadaRepository,
            IAnimalRepository animalRepository) {
        this.vacunaAplicadaRepository = vacunaAplicadaRepository;
        this.animalRepository = animalRepository;
    }

    public VacunaAplicadaResponseDTO ejecutar(RegistrarVacunaCommand command) {
        // 1. Validar existencia del animal
        animalRepository.buscarPorId(command.animalId())
                .orElseThrow(() -> new DomainException("El animal con ID " + command.animalId() + " no existe."));

        // 2. Crear entidad de dominio
        VacunaAplicada nuevaVacuna = new VacunaAplicada(
                null,
                command.animalId(),
                command.veterinarioId(),
                command.nombreVacuna(),
                command.fechaAplicacion(),
                command.fechaProximaDosis()
        );

        // 3. Guardar en repositorio
        VacunaAplicada vacunaGuardada = vacunaAplicadaRepository.guardar(nuevaVacuna);

        // 4. Retornar DTO
        return VacunaAplicadaResponseDTO.fromDomain(vacunaGuardada);
    }
}