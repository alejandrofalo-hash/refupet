package co.ufps.refupet.persistencia.animal.observacion;

import co.ufps.refupet.animal.observacion.IObservacionRepository;
import co.ufps.refupet.animal.observacion.Observacion;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ObservacionRepositoryAdapter implements IObservacionRepository {

    @Inject
    ObservacionRepositoryJpa jpaRepository;

    @Override
    @Transactional
    public void guardar(Observacion observacion) {
        ObservacionJpa entity = new ObservacionJpa();
        entity.setId(observacion.getId());
        entity.setAnimalId(observacion.getAnimalId());
        entity.setAutorId(observacion.getAutorId());
        entity.setDescripcion(observacion.getDescripcion());
        entity.setFechaCreacion(observacion.getFechaCreacion());

        // Panache usa persist() en lugar de save()
        jpaRepository.persist(entity);
    }

    @Override
    public List<Observacion> buscarPorAnimalId(String animalId) {
        return jpaRepository.findByAnimalIdOrderByFechaCreacionDesc(animalId)
                .stream()
                .map(this::mapearADominio)
                .collect(Collectors.toList());
    }

    // Método auxiliar para reconstruir el objeto de dominio desde la entidad JPA
    private Observacion mapearADominio(ObservacionJpa entity) {
        return Observacion.registrar(
                entity.getAnimalId(),
                entity.getAutorId(),
                entity.getDescripcion());
    }
}