package pe.edu.utp.microanalisis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repo;

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
