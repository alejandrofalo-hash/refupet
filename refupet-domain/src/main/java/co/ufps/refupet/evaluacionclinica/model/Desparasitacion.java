package co.ufps.refupet.evaluacionclinica.model;

import java.time.LocalDate;

public class Desparasitacion {
    private Long id;
    private Long animalId;
    private Long veterinarioId;
    private String producto;
    private String dosis;
    private LocalDate fechaAplicacion;

    public Desparasitacion(Long id, Long animalId, Long veterinarioId,
                           String producto, String dosis, LocalDate fechaAplicacion) {
        this.id = id;
        this.animalId = animalId;
        this.veterinarioId = veterinarioId;
        this.producto = producto;
        this.dosis = dosis;
        this.fechaAplicacion = fechaAplicacion != null ? fechaAplicacion : LocalDate.now();
    }

    public Long getId() { return id; }
    public Long getAnimalId() { return animalId; }
    public Long getVeterinarioId() { return veterinarioId; }
    public String getProducto() { return producto; }
    public String getDosis() { return dosis; }
    public LocalDate getFechaAplicacion() { return fechaAplicacion; }
}