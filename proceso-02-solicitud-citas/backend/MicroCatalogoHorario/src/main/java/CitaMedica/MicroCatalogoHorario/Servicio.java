package CitaMedica.MicroCatalogoHorario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    // Buscar horario por ID
    public Horario buscar(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    // Solo horarios disponibles (para reservar)
    public List<Horario> listarDisponibles(Long idMed) {
        return repositorio.findByIdMedicoAndEstado(idMed, "DISPONIBLE");
    }

    // Todos los horarios del médico sin importar el estado
    public List<Horario> listarPorMedico(Long idMed) {
        return repositorio.findByIdMedico(idMed);
    }

    // Listar todos los horarios registrados en el sistema
    public List<Horario> listar() {
        return repositorio.findAll();
    }
}