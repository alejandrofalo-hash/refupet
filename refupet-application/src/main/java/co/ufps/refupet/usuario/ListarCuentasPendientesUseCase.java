
package co.ufps.refupet.usuario;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ListarCuentasPendientesUseCase {

    private final IUsuarioRepository usuarioRepository;

    @Inject
    public ListarCuentasPendientesUseCase(
            IUsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> ejecutar() {

        return usuarioRepository.findPendientes()
                .stream()
                .map(UsuarioResponseDTO::fromDomain)
                .toList();
    }
}