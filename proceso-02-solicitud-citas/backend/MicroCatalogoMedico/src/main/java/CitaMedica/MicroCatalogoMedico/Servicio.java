package CitaMedica.MicroCatalogoMedico;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    public Medico buscar(Long idMedico) {
        return repositorio.findById(idMedico).orElse(null);
    }

    public List<Medico> listarPorEspecialidad(String esp) {
        return repositorio.findByEspecialidad(esp);
    }

    public List<String> listarEspecialidades() {
        return repositorio.findDistinctEspecialidades();
    }

    public List<Medico> listar() {
        return repositorio.findAll();
    }
}