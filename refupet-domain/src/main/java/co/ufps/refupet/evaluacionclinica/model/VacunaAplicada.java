package co.ufps.refupet.evaluacionclinica.model;

import java.time.LocalDate;

public class VacunaAplicada {
    private Long id;
    private Long animalId;
    private Long veterinarioId;
    private String nombreVacuna;
    private LocalDate fechaAplicacion;
    private LocalDate fechaProximaDosis;

    public VacunaAplicada(Long id, Long animalId, Long veterinarioId,
                          String nombreVacuna, LocalDate fechaAplicacion,
                          LocalDate fechaProximaDosis) {
        this.id = id;
        this.animalId = animalId;
        this.veterinarioId = veterinarioId;
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion != null ? fechaAplicacion : LocalDate.now();
        this.fechaProximaDosis = fechaProximaDosis;
    }

    public Long getId() { return id; }
    public Long getAnimalId() { return animalId; }
    public Long getVeterinarioId() { return veterinarioId; }
    public String getNombreVacuna() { return nombreVacuna; }
    public LocalDate getFechaAplicacion() { return fechaAplicacion; }
    public LocalDate getFechaProximaDosis() { return fechaProximaDosis; }
}