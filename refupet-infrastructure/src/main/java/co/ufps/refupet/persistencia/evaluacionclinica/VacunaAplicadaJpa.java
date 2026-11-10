package co.ufps.refupet.persistencia.evaluacionclinica;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacunas_aplicadas")
public class VacunaAplicadaJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "animal_id", nullable = false)
    private Long animalId;

    @Column(name = "veterinario_id", nullable = false)
    private Long veterinarioId;

    @Column(name = "nombre_vacuna", nullable = false)
    private String nombreVacuna;

    @Column(name = "fecha_aplicacion", nullable = false)
    private LocalDate fechaAplicacion;

    @Column(name = "fecha_proxima_dosis")
    private LocalDate fechaProximaDosis;

    public VacunaAplicadaJpa() {}

    public VacunaAplicadaJpa(Long id, Long animalId, Long veterinarioId,
                             String nombreVacuna, LocalDate fechaAplicacion,
                             LocalDate fechaProximaDosis) {
        this.id = id;
        this.animalId = animalId;
        this.veterinarioId = veterinarioId;
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaProximaDosis = fechaProximaDosis;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public Long getVeterinarioId() { return veterinarioId; }
    public void setVeterinarioId(Long veterinarioId) { this.veterinarioId = veterinarioId; }
    public String getNombreVacuna() { return nombreVacuna; }
    public void setNombreVacuna(String nombreVacuna) { this.nombreVacuna = nombreVacuna; }
    public LocalDate getFechaAplicacion() { return fechaAplicacion; }
    public void setFechaAplicacion(LocalDate fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }
    public LocalDate getFechaProximaDosis() { return fechaProximaDosis; }
    public void setFechaProximaDosis(LocalDate fechaProximaDosis) { this.fechaProximaDosis = fechaProximaDosis; }
}