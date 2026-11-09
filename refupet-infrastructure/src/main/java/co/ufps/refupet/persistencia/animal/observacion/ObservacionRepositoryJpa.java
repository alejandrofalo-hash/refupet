package co.ufps.refupet.persistencia.animal.observacion;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ObservacionRepositoryJpa implements PanacheRepository<ObservacionJpa> {

    /**
     * Busca las observaciones de un animal especifico ordenadas por fecha de
     * creacion descendente.
     */
    public List<ObservacionJpa> findByAnimalIdOrderByFechaCreacionDesc(String animalId) {
        return list("animalId = ?1 order by fechaCreacion desc", animalId);
    }
}