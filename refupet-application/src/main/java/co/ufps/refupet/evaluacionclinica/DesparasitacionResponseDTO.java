package co.ufps.refupet.evaluacionclinica;

import co.ufps.refupet.evaluacionclinica.model.Desparasitacion;
import java.time.LocalDate;

public record DesparasitacionResponseDTO(
        Long id,
        Long animalId,
        Long veterinarioId,
        String producto,
        String dosis,
        LocalDate fechaAplicacion
) {
    public static DesparasitacionResponseDTO fromDomain(Desparasitacion desparasitacion) {
        return new DesparasitacionResponseDTO(
                desparasitacion.getId(),
                desparasitacion.getAnimalId(),
                desparasitacion.getVeterinarioId(),
                desparasitacion.getProducto(),
                desparasitacion.getDosis(),
                desparasitacion.getFechaAplicacion()
        );
    }
}