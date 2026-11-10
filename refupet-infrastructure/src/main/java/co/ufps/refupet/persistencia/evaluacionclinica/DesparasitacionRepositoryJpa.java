package co.ufps.refupet.persistencia.evaluacionclinica;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DesparasitacionRepositoryJpa implements PanacheRepository<DesparasitacionJpa> {

    public List<DesparasitacionJpa> findByAnimalId(Long animalId) {
        return list("animalId", animalId);
    }
}