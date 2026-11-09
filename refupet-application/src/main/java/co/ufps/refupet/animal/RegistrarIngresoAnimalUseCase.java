package co.ufps.refupet.animal;

import co.ufps.refupet.DomainException;

// Assuming these are in your refupet-domain module

public class RegistrarIngresoAnimalUseCase {

    private final IAnimalRepository animalRepository;
    private final GeneradorCodigoUnico generadorCodigo;

    public RegistrarIngresoAnimalUseCase(IAnimalRepository animalRepository, GeneradorCodigoUnico generadorCodigo) {
        this.animalRepository = animalRepository;
        this.generadorCodigo = generadorCodigo;
    }

    public AnimalResponseDTO ejecutar(RegistrarAnimalCommand comando) {
        Animal nuevoAnimal = Animal.registrarIngreso(
                comando.getEspecie(),
                comando.getEdadEstimada(),
                comando.getPeso(),
                comando.getFotografiaUrl(),
                generadorCodigo);

        if (animalRepository.existeCodigo(nuevoAnimal.getCodigoUnico())) {
            throw new DomainException("El código generado ya existe.");
        }

        animalRepository.guardar(nuevoAnimal);

        return new AnimalResponseDTO(
                nuevoAnimal.getCodigoUnico(),
                nuevoAnimal.getEstado().name(),
                nuevoAnimal.getFechaMinimaCuarentena());
    }
}