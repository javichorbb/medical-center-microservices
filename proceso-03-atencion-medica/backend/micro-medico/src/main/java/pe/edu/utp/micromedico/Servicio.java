package pe.edu.utp.micromedico;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repo;

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
