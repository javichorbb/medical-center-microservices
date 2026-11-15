package pe.edu.utp.microcestaanalisis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repo;

    private void validarOperacion(String operacionId) {
        if (operacionId == null
                || operacionId.isBlank()
                || operacionId.length() > 100
                || !operacionId.matches("[a-zA-Z0-9_-]+")) {
            throw new IllegalArgumentException("Identificador de operacion invalido");
        }
    }

    public AnalisisCesta agregar(String operacionId, AnalisisCesta item) {
        validarOperacion(operacionId);

        if (item == null || item.getIdAnalisis() == null || item.getIdAnalisis() <= 0) {
            throw new IllegalArgumentException("Codigo de analisis invalido");
        }
        if (item.getIndicacion() == null || item.getIndicacion().isBlank()) {
            throw new IllegalArgumentException("La indicacion es obligatoria");
        }

        return repo.agregar(operacionId, item);
    }

    public boolean quitar(String operacionId, Long idAnalisis) {
        validarOperacion(operacionId);
        if (idAnalisis == null || idAnalisis <= 0) {
            throw new IllegalArgumentException("Codigo de analisis invalido");
        }
        return repo.quitar(operacionId, idAnalisis);
    }

    public List<AnalisisCesta> listar(String operacionId) {
        validarOperacion(operacionId);
        return repo.listar(operacionId);
    }

    public void limpiar(String operacionId) {
        validarOperacion(operacionId);
        repo.limpiar(operacionId);
    }
}
