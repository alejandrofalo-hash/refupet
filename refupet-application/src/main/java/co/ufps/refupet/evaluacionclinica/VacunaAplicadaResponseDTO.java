package co.ufps.refupet.evaluacionclinica;

import co.ufps.refupet.evaluacionclinica.model.VacunaAplicada;
import java.time.LocalDate;

public record VacunaAplicadaResponseDTO(
        Long id,
        Long animalId,
        Long veterinarioId,
        String nombreVacuna,
        LocalDate fechaAplicacion,
        LocalDate fechaProximaDosis
) {
    public static VacunaAplicadaResponseDTO fromDomain(VacunaAplicada vacuna) {
        return new VacunaAplicadaResponseDTO(
                vacuna.getId(),
                vacuna.getAnimalId(),
                vacuna.getVeterinarioId(),
                vacuna.getNombreVacuna(),
                vacuna.getFechaAplicacion(),
                vacuna.getFechaProximaDosis()
        );
    }
}