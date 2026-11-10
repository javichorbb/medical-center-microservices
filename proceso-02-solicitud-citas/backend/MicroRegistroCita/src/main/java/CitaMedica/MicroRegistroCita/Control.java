package CitaMedica.MicroRegistroCita;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

    @Autowired
    private Servicio servicio;

    // 1. Grabar / Registrar una nueva cita médica
    @PostMapping("/registrar")
    public CitaMedica registrar(@RequestBody CitaMedica cita) {
        return servicio.grabar(cita);
    }

    // 2. Listar todas las citas registradas en el sistema
    @GetMapping("/listar")
    public List<CitaMedica> listar() {
        return servicio.listarTodas();
    }

    // 3. Buscar una cita específica por su ID
    @GetMapping("/buscar/{id}")
    public CitaMedica buscar(@PathVariable Long id) {
        return servicio.buscar(id);
    }
}