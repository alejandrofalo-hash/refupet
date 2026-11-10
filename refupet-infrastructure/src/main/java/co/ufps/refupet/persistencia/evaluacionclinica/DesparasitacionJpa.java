package co.ufps.refupet.persistencia.evaluacionclinica;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "desparasitaciones")
public class DesparasitacionJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "animal_id", nullable = false)
    private Long animalId;

    @Column(name = "veterinario_id", nullable = false)
    private Long veterinarioId;

    @Column(nullable = false)
    private String producto;

    private String dosis;

    @Column(name = "fecha_aplicacion", nullable = false)
    private LocalDate fechaAplicacion;

    public DesparasitacionJpa() {}

    public DesparasitacionJpa(Long id, Long animalId, Long veterinarioId,
                              String producto, String dosis, LocalDate fechaAplicacion) {
        this.id = id;
        this.animalId = animalId;
        this.veterinarioId = veterinarioId;
        this.producto = producto;
        this.dosis = dosis;
        this.fechaAplicacion = fechaAplicacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public Long getVeterinarioId() { return veterinarioId; }
    public void setVeterinarioId(Long veterinarioId) { this.veterinarioId = veterinarioId; }
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }
    public LocalDate getFechaAplicacion() { return fechaAplicacion; }
    public void setFechaAplicacion(LocalDate fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }
}