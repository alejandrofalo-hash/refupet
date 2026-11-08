
package co.ufps.refupet.usuario;

public class CrearUsuarioRequest {

    public String nombre;
    public String email;
    public String password;
    public Rol rol;

    public CrearUsuarioRequest() {
    }

    public CrearUsuarioCommand toCommand() {
        return new CrearUsuarioCommand(
                nombre,
                email,
                password,
                rol
        );
    }
}
