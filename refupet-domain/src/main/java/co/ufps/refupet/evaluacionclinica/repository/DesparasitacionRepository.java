package co.ufps.refupet.evaluacionclinica.repository;

import co.ufps.refupet.evaluacionclinica.model.Desparasitacion;
import java.util.List;

public interface DesparasitacionRepository {
    Desparasitacion guardar(Desparasitacion desparasitacion);
    List<Desparasitacion> listarPorAnimalId(Long animalId);
}