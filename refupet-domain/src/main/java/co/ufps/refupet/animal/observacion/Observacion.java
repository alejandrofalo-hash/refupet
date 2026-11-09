package co.ufps.refupet.animal.observacion;

import co.ufps.refupet.DomainException;
import java.time.LocalDateTime;
import java.util.UUID;

public class Observacion {
    private final String id;
    private final String animalId;
    private final String autorId;
    private final String descripcion;
    private final LocalDateTime fechaCreacion;

    // Constructor privado para el factory method
    private Observacion(String id, String animalId, String autorId, String descripcion, LocalDateTime fechaCreacion) {
        this.id = id;
        this.animalId = animalId;
        this.autorId = autorId;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Factory Method: Aplica las reglas de negocio para crear una observación.
     */
    public static Observacion registrar(String animalId, String autorId, String descripcion) {
        if (animalId == null || animalId.trim().isEmpty()) {
            throw new DomainException("El identificador del animal es obligatorio.");
        }
        if (autorId == null || autorId.trim().isEmpty()) {
            throw new DomainException("El identificador del autor (usuario) es obligatorio.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new DomainException("La descripción de la observación no puede estar vacía.");
        }

        return new Observacion(
                UUID.randomUUID().toString(),
                animalId,
                autorId,
                descripcion,
                LocalDateTime.now());
    }

    // Getters (sin setters para inmutabilidad del comportamiento)
    public String getId() {
        return id;
    }

    public String getAnimalId() {
        return animalId;
    }

    public String getAutorId() {
        return autorId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}