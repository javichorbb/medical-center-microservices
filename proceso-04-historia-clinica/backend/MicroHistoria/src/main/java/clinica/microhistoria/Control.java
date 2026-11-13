package clinica.microhistoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/historia")
public class Control {

    @Autowired
    private Servicio servicio;

    @GetMapping("/listar")
    public List<Historia> listar() {
        return servicio.listar();
    }

    @GetMapping("/buscar/{idHistoria}")
    public Historia buscarPorId(@PathVariable Long idHistoria) {
        return servicio.buscarPorId(idHistoria);
    }

    @GetMapping("/paciente/{idPaciente}")
    public Historia buscarPorPaciente(@PathVariable Long idPaciente) {
        return servicio.buscarPorPaciente(idPaciente);
    }

    @PostMapping("/registrar")
    public Historia registrar(@RequestBody Historia historia) {
        return servicio.registrar(historia);
    }
}
