package co.ufps.refupet.persistencia.animal;

import co.ufps.refupet.animal.Animal;
import co.ufps.refupet.animal.AnimalId;
import co.ufps.refupet.animal.IAnimalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class AnimalRepositoryAdapter implements IAnimalRepository {

    private final AnimalRepositoryJpa jpaRepository;

    public AnimalRepositoryAdapter(AnimalRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(Animal animal) {
        AnimalJpa entity = new AnimalJpa();

        entity.setCodigoUnico(animal.getCodigoUnico());
        entity.setEspecie(animal.getEspecie());
        entity.setEdadEstimada(animal.getEdadEstimada());
        entity.setPeso(animal.getPeso());
        entity.setFotografiaUrl(animal.getFotografiaUrl());
        entity.setEstado(animal.getEstado().name());
        entity.setFechaIngreso(animal.getFechaIngreso());
        entity.setFechaMinimaCuarentena(animal.getFechaMinimaCuarentena());

        jpaRepository.persist(entity);;
    }

    @Override
    public boolean existeCodigo(String codigoUnico) {
        return jpaRepository.count("codigoUnico", codigoUnico) > 0;
        
    }

    @Override
    public Optional<Animal> buscarPorId(AnimalId id) {
        return Optional.empty(); // Implementar cuando sea necesario
    }
}