package co.ufps.refupet.animal;

import co.ufps.refupet.DomainException;
import java.util.Objects;

public class AnimalId {
    private final String valor;

    public AnimalId(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new DomainException("El ID del animal no puede ser nulo o estar vacío");
        }
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AnimalId animalId = (AnimalId) o;
        return Objects.equals(valor, animalId.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}