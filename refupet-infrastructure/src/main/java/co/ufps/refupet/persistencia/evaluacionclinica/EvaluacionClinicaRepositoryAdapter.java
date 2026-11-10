package co.ufps.refupet.persistencia.evaluacionclinica;

import co.ufps.refupet.evaluacionclinica.model.EvaluacionClinica;
import co.ufps.refupet.evaluacionclinica.repository.EvaluacionClinicaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class EvaluacionClinicaRepositoryAdapter implements EvaluacionClinicaRepository {

    @Inject
    EvaluacionClinicaRepositoryJpa repositoryJpa;

    @Override
    @Transactional
    public EvaluacionClinica guardar(EvaluacionClinica evaluacion) {
        EvaluacionClinicaJpa jpaEntity = toJpa(evaluacion);
        repositoryJpa.persist(jpaEntity);
        return toDomain(jpaEntity);
    }

    @Override
    public Optional<EvaluacionClinica> buscarPorAnimalId(Long animalId) {
        return repositoryJpa.findByAnimalId(animalId)
                .map(this::toDomain);
    }

    private EvaluacionClinicaJpa toJpa(EvaluacionClinica domain) {
        return new EvaluacionClinicaJpa(
                domain.getId(),
                domain.getAnimalId(),
                domain.getVeterinarioId(),
                domain.getEstadoNutricional(),
                domain.getHeridas(),
                domain.getParasitos(),
                domain.getTemperamento(),
                domain.getFechaRegistro()
        );
    }

    private EvaluacionClinica toDomain(EvaluacionClinicaJpa jpa) {
        return new EvaluacionClinica(
                jpa.getId(),
                jpa.getAnimalId(),
                jpa.getVeterinarioId(),
                jpa.getEstadoNutricional(),
                jpa.getHeridas(),
                jpa.getParasitos(),
                jpa.getTemperamento(),
                jpa.getFechaRegistro()
        );
    }
}