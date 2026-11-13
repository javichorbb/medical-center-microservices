package pe.edu.utp.micromedico.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.micromedico.entidad.Medico;
import pe.edu.utp.micromedico.repositorio.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repo;

    public Medico grabar(Medico medico) {
        return repo.save(medico);
    }

    public Medico buscar(Long idMedico) {
        return repo.findById(idMedico).orElse(null);
    }

    public List<Medico> listar() {
        return repo.findAll();
    }
}
