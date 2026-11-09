package co.ufps.refupet.animal.observacion;

import java.time.LocalDateTime;

public class ObservacionResponseDTO {
    private final String id;
    private final String descripcion;
    private final LocalDateTime fechaCreacion;

    public ObservacionResponseDTO(String id, String descripcion, LocalDateTime fechaCreacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}