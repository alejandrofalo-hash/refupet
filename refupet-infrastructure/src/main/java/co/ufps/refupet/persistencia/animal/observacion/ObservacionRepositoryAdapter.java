package co.ufps.refupet.persistencia.animal.observacion;

import co.ufps.refupet.animal.observacion.IObservacionRepository;
import co.ufps.refupet.animal.observacion.Observacion;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObservacionRepositoryAdapter implements IObservacionRepository {

    private final ObservacionRepositoryJpa jpaRepository;

    public ObservacionRepositoryAdapter(ObservacionRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(Observacion observacion) {
        ObservacionJpa entity = new ObservacionJpa();
        entity.setId(observacion.getId());
        entity.setAnimalId(observacion.getAnimalId());
        entity.setAutorId(observacion.getAutorId());
        entity.setDescripcion(observacion.getDescripcion());
        entity.setFechaCreacion(observacion.getFechaCreacion());

        jpaRepository.save(entity);
    }

    @Override
    public List<Observacion> buscarPorAnimalId(String animalId) {
        // Podríamos mapear de vuelta si hay un caso de uso de lectura, por ahora lo
        // dejamos implementado por contrato.
        return null;
    }
}