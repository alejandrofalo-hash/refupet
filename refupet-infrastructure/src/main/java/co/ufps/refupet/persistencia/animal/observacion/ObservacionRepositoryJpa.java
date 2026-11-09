package co.ufps.refupet.persistencia.animal.observacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservacionRepositoryJpa extends JpaRepository<ObservacionJpa, String> {
    List<ObservacionJpa> findByAnimalIdOrderByFechaCreacionDesc(String animalId);
}