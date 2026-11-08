package co.ufps.refupet.usuario;

import co.ufps.refupet.DomainException;
import java.util.Objects;
import java.util.UUID;

public final class UsuarioId {

    private final String value;

    private UsuarioId(String value) {
        if (value == null || value.isBlank()) {
            throw new DomainException("El usuarioId no puede estar vacío");
        }
        this.value = value.strip();
    }

    public static UsuarioId generate() {
        return new UsuarioId(UUID.randomUUID().toString());
    }

    public static UsuarioId of(String value) {
        return new UsuarioId(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioId usuarioId = (UsuarioId) o;
        return Objects.equals(value, usuarioId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}