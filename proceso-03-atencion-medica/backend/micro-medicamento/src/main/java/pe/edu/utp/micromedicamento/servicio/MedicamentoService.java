package pe.edu.utp.micromedicamento.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.micromedicamento.entidad.Medicamento;
import pe.edu.utp.micromedicamento.repositorio.MedicamentoRepository;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository repo;

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
