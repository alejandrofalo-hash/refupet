package co.ufps.refupet.persistencia.animal;

import co.ufps.refupet.animal.GeneradorCodigoUnico;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class GeneradorCodigoUnicoImpl implements GeneradorCodigoUnico {
    @Override
    public String generar() {
        String randomStr = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "REF-" + randomStr;
    }
}