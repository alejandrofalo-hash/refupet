package co.ufps.refupet.usuario;

import java.util.Objects;
import java.util.UUID;

public final class UsuarioId {

    private final String value;

    public UsuarioId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El ID de usuario no puede estar vacío");
        }
        this.value = value;
    }

    public static UsuarioId generate() {
        return new UsuarioId(UUID.randomUUID().toString());
    }

    public String getValue() {
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