package pe.edu.utp.microcestareceta;

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

    public MedicamentoCesta agregar(String operacionId, MedicamentoCesta item) {
        validarOperacion(operacionId);

        if (item == null || item.getIdMedicamento() == null || item.getIdMedicamento() <= 0) {
            throw new IllegalArgumentException("Codigo de medicamento invalido");
        }
        if (item.getDosis() == null || item.getDosis().isBlank()) {
            throw new IllegalArgumentException("La dosis es obligatoria");
        }
        if (item.getCantidad() == null || item.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }

        return repo.agregar(operacionId, item);
    }

    public boolean quitar(String operacionId, Long idMedicamento) {
        validarOperacion(operacionId);
        if (idMedicamento == null || idMedicamento <= 0) {
            throw new IllegalArgumentException("Codigo de medicamento invalido");
        }
        return repo.quitar(operacionId, idMedicamento);
    }

    public List<MedicamentoCesta> listar(String operacionId) {
        validarOperacion(operacionId);
        return repo.listar(operacionId);
    }

    public void limpiar(String operacionId) {
        validarOperacion(operacionId);
        repo.limpiar(operacionId);
    }
}
