package co.ufps.refupet.persistencia.animal;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AnimalRepositoryJpa implements PanacheRepository<AnimalJpa> {

    /**
     * Verifica si existe un animal con el código único especificado.
     */
    public boolean existsByCodigoUnico(String codigoUnico) {
        return count("codigoUnico", codigoUnico) > 0;
    }
}