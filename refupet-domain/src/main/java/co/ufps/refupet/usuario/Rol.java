package co.ufps.refupet.usuario;

import java.util.Set;

public enum Rol {
    ADMINISTRADOR(Set.of(
            Permiso.GESTIONAR_USUARIOS,
            Permiso.APROBAR_ADOPCION,
            Permiso.ASIGNAR_HOGAR_PASO,
            Permiso.REGISTRAR_RESCATE
    )),
    VETERINARIO(Set.of(
            Permiso.REGISTRAR_VACUNA
    )),
    VOLUNTARIO(Set.of(
            Permiso.REGISTRAR_RESCATE
    )),
    SOLICITANTE(Set.of(
            Permiso.SOLICITAR_ADOPCION
    ));

    private final Set<Permiso> permisos;

    Rol(Set<Permiso> permisos) {
        this.permisos = permisos;
    }

    public Set<Permiso> getPermisos() {
        return permisos;
    }

    public boolean tienePermiso(Permiso permiso) {
        return permisos.contains(permiso);
    }
}