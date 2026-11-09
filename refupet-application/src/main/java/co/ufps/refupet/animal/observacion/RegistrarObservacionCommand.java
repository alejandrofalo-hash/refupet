package co.ufps.refupet.animal.observacion;

public class RegistrarObservacionCommand {
    private final String animalId;
    private final String autorId;
    private final String descripcion;

    public RegistrarObservacionCommand(String animalId, String autorId, String descripcion) {
        this.animalId = animalId;
        this.autorId = autorId;
        this.descripcion = descripcion;
    }

    public String getAnimalId() {
        return animalId;
    }

    public String getAutorId() {
        return autorId;
    }

    public String getDescripcion() {
        return descripcion;
    }
}