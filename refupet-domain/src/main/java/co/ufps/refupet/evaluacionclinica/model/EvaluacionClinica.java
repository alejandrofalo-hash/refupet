package co.ufps.refupet.evaluacionclinica.model;

import java.time.LocalDateTime;

public class EvaluacionClinica {
    private Long id;
    private Long animalId;
    private Long veterinarioId;
    private EstadoNutricional estadoNutricional;
    private String heridas;
    private String parasitos;
    private Temperamento temperamento;
    private LocalDateTime fechaRegistro;

    public EvaluacionClinica(Long id, Long animalId, Long veterinarioId,
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
        this.fechaRegistro = fechaRegistro != null ? fechaRegistro : LocalDateTime.now();
    }

    // Getters
    public Long getId() { return id; }
    public Long getAnimalId() { return animalId; }
    public Long getVeterinarioId() { return veterinarioId; }
    public EstadoNutricional getEstadoNutricional() { return estadoNutricional; }
    public String getHeridas() { return heridas; }
    public String getParasitos() { return parasitos; }
    public Temperamento getTemperamento() { return temperamento; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
}