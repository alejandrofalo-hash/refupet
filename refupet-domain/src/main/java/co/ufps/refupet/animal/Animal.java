package co.ufps.refupet.animal;

import co.ufps.refupet.DomainException;
import java.time.LocalDate;
import java.util.Objects;

public class Animal {
    private AnimalId id;
    private String codigoUnico;
    private String especie;
    private Integer edadEstimada;
    private Double peso;
    private String fotografiaUrl;
    private EstadoAnimal estado;
    private LocalDate fechaIngreso;
    private LocalDate fechaMinimaCuarentena;

    // Constructor vacío privado para obligar a usar el método de creación (factory
    // method)
    private Animal() {
    }

    /**
     * Método de fábrica para registrar el ingreso de un nuevo animal.
     * Aplica las reglas de negocio de la HU-01.
     */
    public static Animal registrarIngreso(
            String especie,
            Integer edadEstimada,
            Double peso,
            String fotografiaUrl,
            GeneradorCodigoUnico generadorCodigo) {

        // Validaciones de negocio
        if (especie == null || especie.trim().isEmpty()) {
            throw new DomainException("La especie del animal es obligatoria");
        }
        if (peso == null || peso <= 0) {
            throw new DomainException("El peso debe ser mayor a 0");
        }

        Animal animal = new Animal();
        animal.especie = especie;
        animal.edadEstimada = edadEstimada;
        animal.peso = peso;
        animal.fotografiaUrl = fotografiaUrl;

        // Generación de código
        animal.codigoUnico = generadorCodigo.generar();
        animal.fechaIngreso = LocalDate.now();

        // Reglas estrictas: Estado automático y cálculo de cuarentena (15 días mínimo)
        animal.estado = EstadoAnimal.EN_CUARENTENA;
        animal.fechaMinimaCuarentena = animal.fechaIngreso.plusDays(15);

        return animal;
    }

    // --- Métodos de comportamiento adicionales ---

    public void asignarId(AnimalId id) {
        if (this.id != null) {
            throw new DomainException("El ID del animal ya ha sido asignado");
        }
        this.id = id;
    }

    // --- Getters (Los Setters se omiten para proteger la consistencia de la
    // entidad) ---

    public AnimalId getId() {
        return id;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public String getEspecie() {
        return especie;
    }

    public Integer getEdadEstimada() {
        return edadEstimada;
    }

    public Double getPeso() {
        return peso;
    }

    public String getFotografiaUrl() {
        return fotografiaUrl;
    }

    public EstadoAnimal getEstado() {
        return estado;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFechaMinimaCuarentena() {
        return fechaMinimaCuarentena;
    }
}