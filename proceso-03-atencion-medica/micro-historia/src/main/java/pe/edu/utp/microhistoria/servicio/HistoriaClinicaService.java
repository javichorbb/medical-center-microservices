package pe.edu.utp.microhistoria.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.microhistoria.entidad.HistoriaClinica;
import pe.edu.utp.microhistoria.repositorio.HistoriaClinicaRepository;

@Service
public class HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository repo;

    public HistoriaClinica grabar(HistoriaClinica historia) {
        return repo.save(historia);
    }

    public HistoriaClinica buscar(Long idHistoria) {
        return repo.findById(idHistoria).orElse(null);
    }

    public HistoriaClinica buscarPorPaciente(Long idPaciente) {
        return repo.findByIdPaciente(idPaciente).orElse(null);
    }

    public List<HistoriaClinica> listar() {
        return repo.findAll();
    }
}
