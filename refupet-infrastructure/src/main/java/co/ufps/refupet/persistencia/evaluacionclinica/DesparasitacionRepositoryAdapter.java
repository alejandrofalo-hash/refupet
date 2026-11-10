package co.ufps.refupet.persistencia.evaluacionclinica;

import co.ufps.refupet.evaluacionclinica.model.Desparasitacion;
import co.ufps.refupet.evaluacionclinica.repository.DesparasitacionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DesparasitacionRepositoryAdapter implements DesparasitacionRepository {

    @Inject
    DesparasitacionRepositoryJpa repositoryJpa;

    @Override
    @Transactional
    public Desparasitacion guardar(Desparasitacion desparasitacion) {
        DesparasitacionJpa jpaEntity = toJpa(desparasitacion);
        repositoryJpa.persist(jpaEntity);
        return toDomain(jpaEntity);
    }

    @Override
    public List<Desparasitacion> listarPorAnimalId(Long animalId) {
        return repositoryJpa.findByAnimalId(animalId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private DesparasitacionJpa toJpa(Desparasitacion domain) {
        return new DesparasitacionJpa(
                domain.getId(),
                domain.getAnimalId(),
                domain.getVeterinarioId(),
                domain.getProducto(),
                domain.getDosis(),
                domain.getFechaAplicacion()
        );
    }

    private Desparasitacion toDomain(DesparasitacionJpa jpa) {
        return new Desparasitacion(
                jpa.getId(),
                jpa.getAnimalId(),
                jpa.getVeterinarioId(),
                jpa.getProducto(),
                jpa.getDosis(),
                jpa.getFechaAplicacion()
        );
    }
}