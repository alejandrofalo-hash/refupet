package co.ufps.refupet.evaluacionclinica.repository;

import co.ufps.refupet.evaluacionclinica.model.VacunaAplicada;
import java.util.List;

public interface VacunaAplicadaRepository {
    VacunaAplicada guardar(VacunaAplicada vacuna);
    List<VacunaAplicada> listarPorAnimalId(Long animalId);
}