package co.ufps.refupet.persistencia.animal;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "animales")
public class AnimalJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "codigo_unico", unique = true, nullable = false)
    private String codigoUnico;

    @Column(nullable = false)
    private String especie;

    @Column(name = "edad_estimada")
    private Integer edadEstimada;

    @Column(nullable = false)
    private Double peso;

    @Column(name = "fotografia_url")
    private String fotografiaUrl;

    @Column(nullable = false)
    private String estado;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(name = "fecha_minima_cuarentena", nullable = false)
    private LocalDate fechaMinimaCuarentena;

    // Constructor vacío requerido por JPA
    public AnimalJpa() {
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getEdadEstimada() {
        return edadEstimada;
    }

    public void setEdadEstimada(Integer edadEstimada) {
        this.edadEstimada = edadEstimada;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getFotografiaUrl() {
        return fotografiaUrl;
    }

    public void setFotografiaUrl(String fotografiaUrl) {
        this.fotografiaUrl = fotografiaUrl;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaMinimaCuarentena() {
        return fechaMinimaCuarentena;
    }

    public void setFechaMinimaCuarentena(LocalDate fechaMinimaCuarentena) {
        this.fechaMinimaCuarentena = fechaMinimaCuarentena;
    }
}