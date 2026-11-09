package co.ufps.refupet.persistencia.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepositoryJpa extends JpaRepository<AnimalJpa, String> {
    boolean existsByCodigoUnico(String codigoUnico);
}