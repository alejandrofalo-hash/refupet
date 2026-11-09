package co.ufps.refupet.animal;

public class RegistrarAnimalCommand {
    private String especie;
    private Integer edadEstimada;
    private Double peso;
    private String fotografiaUrl;

    public RegistrarAnimalCommand(String especie, Integer edadEstimada, Double peso, String fotografiaUrl) {
        this.especie = especie;
        this.edadEstimada = edadEstimada;
        this.peso = peso;
        this.fotografiaUrl = fotografiaUrl;
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
}