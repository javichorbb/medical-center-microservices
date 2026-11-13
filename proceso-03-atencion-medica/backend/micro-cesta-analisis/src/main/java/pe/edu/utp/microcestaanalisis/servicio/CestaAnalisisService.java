package pe.edu.utp.microcestaanalisis.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.microcestaanalisis.dto.AnalisisCestaDTO;
import pe.edu.utp.microcestaanalisis.repositorio.CestaAnalisisRepository;

@Service
public class CestaAnalisisService {

    @Autowired
    private CestaAnalisisRepository repositorio;

    private void validarOperacion(String operacionId) {

        if (operacionId == null
                || operacionId.isBlank()
                || operacionId.length() > 100
                || !operacionId.matches("[a-zA-Z0-9_-]+")) {

            throw new IllegalArgumentException(
                "Identificador de operacion invalido");
        }
    }

    // Agregar analisis a la cesta
    public AnalisisCestaDTO agregar(
            String operacionId,
            AnalisisCestaDTO item) {

        validarOperacion(operacionId);

        if (item == null
                || item.getIdAnalisis() == null
                || item.getIdAnalisis() <= 0) {

            throw new IllegalArgumentException(
                "Codigo de analisis invalido");
        }

        if (item.getIndicacion() == null
                || item.getIndicacion().isBlank()) {

            throw new IllegalArgumentException(
                "La indicacion es obligatoria");
        }

        return repositorio.agregar(operacionId, item);
    }

    // Quitar analisis de la cesta
    public boolean quitar(
            String operacionId,
            Long idAnalisis) {

        validarOperacion(operacionId);

        if (idAnalisis == null || idAnalisis <= 0) {

            throw new IllegalArgumentException(
                "Codigo de analisis invalido");
        }

        return repositorio.quitar(
            operacionId, idAnalisis);
    }

    // Consultar la cesta
    public List<AnalisisCestaDTO> listar(
            String operacionId) {

        validarOperacion(operacionId);
        return repositorio.listar(operacionId);
    }

    // Limpiar la cesta
    public void limpiar(String operacionId) {

        validarOperacion(operacionId);
        repositorio.limpiar(operacionId);
    }
}
