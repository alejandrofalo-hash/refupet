package co.ufps.refupet.persistencia.evaluacionclinica;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class VacunaAplicadaRepositoryJpa implements PanacheRepository<VacunaAplicadaJpa> {

    public List<VacunaAplicadaJpa> findByAnimalId(Long animalId) {
        return list("animalId", animalId);
    }
}