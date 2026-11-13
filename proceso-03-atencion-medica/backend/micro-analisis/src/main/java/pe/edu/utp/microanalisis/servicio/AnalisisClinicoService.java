package pe.edu.utp.microanalisis.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.utp.microanalisis.entidad.AnalisisClinico;
import pe.edu.utp.microanalisis.repositorio.AnalisisClinicoRepository;

@Service
public class AnalisisClinicoService {

    @Autowired
    private AnalisisClinicoRepository repo;

    public AnalisisClinico grabar(AnalisisClinico analisis) {
        return repo.save(analisis);
    }

    public AnalisisClinico buscar(Long idAnalisis) {
        return repo.findById(idAnalisis).orElse(null);
    }

    public List<AnalisisClinico> listar() {
        return repo.findAll();
    }
}
