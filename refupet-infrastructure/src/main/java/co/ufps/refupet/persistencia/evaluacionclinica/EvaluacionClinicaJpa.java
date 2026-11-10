package co.ufps.refupet.persistencia.evaluacionclinica;

import co.ufps.refupet.evaluacionclinica.model.EstadoNutricional;
import co.ufps.refupet.evaluacionclinica.model.Temperamento;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluaciones_clinicas")
public class EvaluacionClinicaJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "animal_id", nullable = false)
    private Long animalId;

    @Column(name = "veterinario_id", nullable = false)
    private Long veterinarioId;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_nutricional", nullable = false)
    private EstadoNutricional estadoNutricional;

    @Column(columnDefinition = "TEXT")
    private String heridas;

    @Column(columnDefinition = "TEXT")
    private String parasitos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Temperamento temperamento;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    public EvaluacionClinicaJpa() {}

    public EvaluacionClinicaJpa(Long id, Long animalId, Long veterinarioId,
                                EstadoNutricional estadoNutricional, String heridas,
                                String parasitos, Temperamento temperamento,
                                LocalDateTime fechaRegistro) {
        this.id = id;
        this.animalId = animalId;
        this.veterinarioId = veterinarioId;
        this.estadoNutricional = estadoNutricional;
        this.heridas = heridas;
        this.parasitos = parasitos;
        this.temperamento = temperamento;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }

    public Long getVeterinarioId() { return veterinarioId; }
    public void setVeterinarioId(Long veterinarioId) { this.veterinarioId = veterinarioId; }

    public EstadoNutricional getEstadoNutricional() { return estadoNutricional; }
    public void setEstadoNutricional(EstadoNutricional estadoNutricional) { this.estadoNutricional = estadoNutricional; }

    public String getHeridas() { return heridas; }
    public void setHeridas(String heridas) { this.heridas = heridas; }

    public String getParasitos() { return parasitos; }
    public void setParasitos(String parasitos) { this.parasitos = parasitos; }

    public Temperamento getTemperamento() { return temperamento; }
    public void setTemperamento(Temperamento temperamento) { this.temperamento = temperamento; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}