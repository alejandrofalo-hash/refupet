package co.ufps.refupet.evaluacionclinica;

import co.ufps.refupet.animal.IAnimalRepository;
import co.ufps.refupet.DomainException;
import co.ufps.refupet.evaluacionclinica.model.Desparasitacion;
import co.ufps.refupet.evaluacionclinica.repository.DesparasitacionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RegistrarDesparasitacionUseCase {

    private final DesparasitacionRepository desparasitacionRepository;
    private final IAnimalRepository animalRepository;

    @Inject
    public RegistrarDesparasitacionUseCase(
            DesparasitacionRepository desparasitacionRepository,
            IAnimalRepository animalRepository) {
        this.desparasitacionRepository = desparasitacionRepository;
        this.animalRepository = animalRepository;
    }

    public DesparasitacionResponseDTO ejecutar(RegistrarDesparasitacionCommand command) {
        // 1. Validar existencia del animal
        animalRepository.buscarPorId(command.animalId())
                .orElseThrow(() -> new DomainException("El animal con ID " + command.animalId() + " no existe."));

        // 2. Crear entidad de dominio
        Desparasitacion nuevaDesparasitacion = new Desparasitacion(
                null,
                command.animalId(),
                command.veterinarioId(),
                command.producto(),
                command.dosis(),
                command.fechaAplicacion()
        );

        // 3. Persistir en repositorio
        Desparasitacion guardada = desparasitacionRepository.guardar(nuevaDesparasitacion);

        // 4. Retornar DTO de respuesta
        return DesparasitacionResponseDTO.fromDomain(guardada);
    }
}