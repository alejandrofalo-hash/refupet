package co.ufps.refupet.persistencia.evaluacionclinica;

import co.ufps.refupet.evaluacionclinica.model.VacunaAplicada;
import co.ufps.refupet.evaluacionclinica.repository.VacunaAplicadaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class VacunaAplicadaRepositoryAdapter implements VacunaAplicadaRepository {

    @Inject
    VacunaAplicadaRepositoryJpa repositoryJpa;

    @Override
    @Transactional
    public VacunaAplicada guardar(VacunaAplicada vacuna) {
        VacunaAplicadaJpa jpaEntity = toJpa(vacuna);
        repositoryJpa.persist(jpaEntity);
        return toDomain(jpaEntity);
    }

    @Override
    public List<VacunaAplicada> listarPorAnimalId(Long animalId) {
        return repositoryJpa.findByAnimalId(animalId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private VacunaAplicadaJpa toJpa(VacunaAplicada domain) {
        return new VacunaAplicadaJpa(
                domain.getId(),
                domain.getAnimalId(),
                domain.getVeterinarioId(),
                domain.getNombreVacuna(),
                domain.getFechaAplicacion(),
                domain.getFechaProximaDosis()
        );
    }

    private VacunaAplicada toDomain(VacunaAplicadaJpa jpa) {
        return new VacunaAplicada(
                jpa.getId(),
                jpa.getAnimalId(),
                jpa.getVeterinarioId(),
                jpa.getNombreVacuna(),
                jpa.getFechaAplicacion(),
                jpa.getFechaProximaDosis()
        );
    }
}