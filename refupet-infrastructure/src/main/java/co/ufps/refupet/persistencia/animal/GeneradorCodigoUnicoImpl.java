package co.ufps.refupet.util;

import co.ufps.refupet.animal.GeneradorCodigoUnico;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class GeneradorCodigoUnicoImpl implements GeneradorCodigoUnico {
    @Override
    public String generar() {
        String randomStr = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "REF-" + randomStr;
    }
}