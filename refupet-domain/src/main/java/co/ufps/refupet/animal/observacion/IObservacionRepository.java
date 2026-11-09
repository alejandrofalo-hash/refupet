package co.ufps.refupet.animal.observacion;

import java.util.List;

public interface IObservacionRepository {
    void guardar(Observacion observacion);

    List<Observacion> buscarPorAnimalId(String animalId);
}