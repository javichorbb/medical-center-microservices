package CitaMedica.MicroCatalogoHorario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

    @Autowired
    private Servicio servicio;

    // Buscar un horario específico por ID
    @GetMapping("/buscar/{id}")
    public Horario buscar(@PathVariable Long id) {
        return servicio.buscar(id);
    }

    // 1. Solo horarios disponibles del médico (para que el paciente reserve)
    @GetMapping("/disponibles/{idMed}")
    public List<Horario> listarDisponibles(@PathVariable Long idMed) {
        return servicio.listarDisponibles(idMed);
    }

    // 2. Todos los horarios del médico (incluyendo reservados, ocupados, etc.)
    @GetMapping("/porMedico/{idMed}")
    public List<Horario> listarPorMedico(@PathVariable Long idMed) {
        return servicio.listarPorMedico(idMed);
    }

    // 3. Todos los horarios de la clínica
    @GetMapping("/listar")
    public List<Horario> listar() {
        return servicio.listar();
    }
}