package pe.edu.utp.microcestareceta;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    private List<MedicamentoCesta> cesta = new ArrayList<>();

    @Autowired
    private Repositorio repo;

    public List<MedicamentoCesta> agregar(MedicamentoCesta item) {
        cesta = repo.getCesta();
        cesta.removeIf(m -> m.getIdMedicamento() != null && m.getIdMedicamento().equals(item.getIdMedicamento()));
        cesta.add(item);
        return cesta;
    }

    public List<MedicamentoCesta> quitar(Long idMedicamento) {
        cesta = repo.getCesta();
        cesta.removeIf(m -> m.getIdMedicamento() != null && m.getIdMedicamento().equals(idMedicamento));
        return cesta;
    }

    public List<MedicamentoCesta> listar() {
        cesta = repo.getCesta();
        return cesta;
    }

    public void limpiar() {
        cesta = repo.getCesta();
        cesta.clear();
    }
}
