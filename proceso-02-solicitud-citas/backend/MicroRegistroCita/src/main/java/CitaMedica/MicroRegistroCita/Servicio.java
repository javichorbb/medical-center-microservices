package CitaMedica.MicroRegistroCita;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    public CitaMedica grabar(CitaMedica cita) {
        cita.setEstado("RESERVADA");
        return repositorio.save(cita);
    }

    public List<CitaMedica> listarTodas() {
        return repositorio.findAll();
    }

    public CitaMedica buscar(Long id) {
        return repositorio.findById(id).orElse(null);
    }
}