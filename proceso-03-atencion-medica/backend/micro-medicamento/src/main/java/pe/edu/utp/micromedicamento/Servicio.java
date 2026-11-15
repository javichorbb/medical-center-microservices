package pe.edu.utp.micromedicamento;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repo;

    public Medicamento grabar(Medicamento medicamento) {
        return repo.save(medicamento);
    }

    public Medicamento buscar(Long idMedicamento) {
        return repo.findById(idMedicamento).orElse(null);
    }

    public List<Medicamento> listar() {
        return repo.findAll();
    }
}
