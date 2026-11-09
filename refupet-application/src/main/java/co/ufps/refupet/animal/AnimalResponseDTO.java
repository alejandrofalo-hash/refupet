package co.ufps.refupet.animal;

import java.time.LocalDate;

public class AnimalResponseDTO {
    private final String codigoUnico;
    private final String estado;
    private final LocalDate fechaMinimaCuarentena;

    public AnimalResponseDTO(String codigoUnico, String estado, LocalDate fechaMinimaCuarentena) {
        this.codigoUnico = codigoUnico;
        this.estado = estado;
        this.fechaMinimaCuarentena = fechaMinimaCuarentena;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFechaMinimaCuarentena() {
        return fechaMinimaCuarentena;
    }
}