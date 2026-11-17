package CitaMedica.MicroCatalogoHorario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    // Grabar horario
    public Horario grabar(Horario h) {
        return repositorio.save(h);
    }

    // Buscar horario por ID
    public Horario buscar(Long idHorario) {
        return repositorio.findById(idHorario).orElse(null);
    }

    // Solo horarios disponibles (para reservar)
    public List<Horario> listarDisponibles(Long idMedico) {
        return repositorio.findByIdMedicoAndEstado(idMedico, "DISPONIBLE");
    }

    // Todos los horarios del médico sin importar el estado
    public List<Horario> listarPorMedico(Long idMedico) {
        return repositorio.findByIdMedico(idMedico);
    }

    // Listar todos los horarios registrados en el sistema
    public List<Horario> listar() {
        return repositorio.findAll();
    }
}