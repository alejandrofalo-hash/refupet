package co.ufps.refupet.usuario;

public class UsuarioResponseDTO {

    private final String id;
    private final String nombre;
    private final String email;
    private final String rol;
    private final String estadoUsuario;

    public UsuarioResponseDTO(String id, String nombre, String email, String rol, String estadoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.estadoUsuario = estadoUsuario;
    }

    public static UsuarioResponseDTO fromDomain(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getUsuarioId().getValue(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().name(),
                usuario.getEstadoUsuario().name()
        );
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
    public String getEstadoUsuario() { return estadoUsuario; }
}