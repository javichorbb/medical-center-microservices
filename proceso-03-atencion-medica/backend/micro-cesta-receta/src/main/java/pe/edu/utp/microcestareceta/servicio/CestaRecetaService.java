package pe.edu.utp.microcestareceta.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.microcestareceta.dto.MedicamentoCestaDTO;
import pe.edu.utp.microcestareceta.repositorio.CestaRecetaRepository;

@Service
public class CestaRecetaService {

    @Autowired
    private CestaRecetaRepository repositorio;

    private void validarOperacion(String operacionId) {

        if (operacionId == null
                || operacionId.isBlank()
                || operacionId.length() > 100
                || !operacionId.matches("[a-zA-Z0-9_-]+")) {

            throw new IllegalArgumentException(
                "Identificador de operacion invalido");
        }
    }

    public MedicamentoCestaDTO agregar(
            String operacionId,
            MedicamentoCestaDTO item) {

        validarOperacion(operacionId);

        if (item == null
                || item.getIdMedicamento() == null
                || item.getIdMedicamento() <= 0) {

            throw new IllegalArgumentException(
                "Codigo de medicamento invalido");
        }

        if (item.getDosis() == null
                || item.getDosis().isBlank()) {

            throw new IllegalArgumentException(
                "La dosis es obligatoria");
        }

        if (item.getCantidad() == null
                || item.getCantidad() <= 0) {

            throw new IllegalArgumentException(
                "La cantidad debe ser mayor que cero");
        }

        return repositorio.agregar(operacionId, item);
    }

    public boolean quitar(
            String operacionId,
            Long idMedicamento) {

        validarOperacion(operacionId);

        if (idMedicamento == null
                || idMedicamento <= 0) {
            throw new IllegalArgumentException(
                "Codigo de medicamento invalido");
        }

        return repositorio.quitar(
                operacionId, idMedicamento);
    }

    public List<MedicamentoCestaDTO> listar(
            String operacionId) {

        validarOperacion(operacionId);
        return repositorio.listar(operacionId);
    }

    public void limpiar(String operacionId) {
        validarOperacion(operacionId);
        repositorio.limpiar(operacionId);
    }
}
