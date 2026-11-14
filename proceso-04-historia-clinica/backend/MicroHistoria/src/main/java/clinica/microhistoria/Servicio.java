package clinica.microhistoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    public List<Historia> listar() {
        return repositorio.findAll();
    }

    public Historia buscarPorId(Long idHistoria) {
        return repositorio.findById(idHistoria).orElse(null);
    }

    public Historia buscarPorPaciente(Long idPaciente) {
        return repositorio.findByIdPaciente(idPaciente).orElse(null);
    }

    public Historia registrar(Historia historia) {
        historia.setFechaApertura(LocalDate.now());
        return repositorio.save(historia);
    }
}
