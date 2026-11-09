package co.ufps.refupet.animal;

import java.util.Optional;

public interface IAnimalRepository {
    void guardar(Animal animal);

    boolean existeCodigo(String codigoUnico);

    Optional<Animal> buscarPorId(AnimalId id);
}