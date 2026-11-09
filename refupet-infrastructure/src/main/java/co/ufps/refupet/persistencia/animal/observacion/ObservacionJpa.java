package co.ufps.refupet.persistencia.animal.observacion;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "observaciones", indexes = {
        @Index(name = "idx_observacion_animal", columnList = "animal_id"),
        @Index(name = "idx_observacion_fecha", columnList = "fecha_creacion")
})
public class ObservacionJpa {

    @Id
    private String id;

    @Column(name = "animal_id", nullable = false)
    private String animalId;

    @Column(name = "autor_id", nullable = false)
    private String autorId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    // Constructor vacío requerido por JPA
    public ObservacionJpa() {
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getAutorId() {
        return autorId;
    }

    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}