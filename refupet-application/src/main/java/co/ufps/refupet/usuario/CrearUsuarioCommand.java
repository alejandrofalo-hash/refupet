package co.ufps.refupet.usuario;

public class CrearUsuarioCommand {

    private final String nombre;
    private final String email;
    private final String password;
    private final Rol rol;

    public CrearUsuarioCommand(String nombre, String email, String password, Rol rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Rol getRol() { return rol; }
}
