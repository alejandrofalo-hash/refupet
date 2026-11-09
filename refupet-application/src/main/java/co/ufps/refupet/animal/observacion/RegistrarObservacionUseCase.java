package co.ufps.refupet.animal.observacion;

import co.ufps.refupet.DomainException;
import co.ufps.refupet.animal.IAnimalRepository; // Viene de la HU-01

public class RegistrarObservacionUseCase {

    private final IObservacionRepository observacionRepository;
    private final IAnimalRepository animalRepository;

    public RegistrarObservacionUseCase(IObservacionRepository observacionRepository,
            IAnimalRepository animalRepository) {
        this.observacionRepository = observacionRepository;
        this.animalRepository = animalRepository;
    }

    public ObservacionResponseDTO ejecutar(RegistrarObservacionCommand comando) {
        // 1. Regla de negocio cruzada: Validar que el animal exista
        boolean existeAnimal = animalRepository.existeCodigo(comando.getAnimalId()); // Asumiendo que animalId es el
                                                                                     // codigoUnico o un ID técnico,
                                                                                     // ajusta según decidas
        if (!existeAnimal) {
            throw new DomainException("El animal especificado no existe en el sistema.");
        }

        // 2. Crear la entidad de dominio
        Observacion nuevaObservacion = Observacion.registrar(
                comando.getAnimalId(),
                comando.getAutorId(),
                comando.getDescripcion());

        // 3. Persistir (Notar que NO alteramos el estado del animal aquí, cumpliendo el
        // criterio de aceptación)
        observacionRepository.guardar(nuevaObservacion);

        // 4. Retornar DTO
        return new ObservacionResponseDTO(
                nuevaObservacion.getId(),
                nuevaObservacion.getDescripcion(),
                nuevaObservacion.getFechaCreacion());
    }
}