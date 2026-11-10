package co.ufps.refupet.persistencia.evaluacionclinica;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class EvaluacionClinicaRepositoryJpa implements PanacheRepository<EvaluacionClinicaJpa> {

    public Optional<EvaluacionClinicaJpa> findByAnimalId(Long animalId) {
        return find("animalId", animalId).firstResultOptional();
    }
}