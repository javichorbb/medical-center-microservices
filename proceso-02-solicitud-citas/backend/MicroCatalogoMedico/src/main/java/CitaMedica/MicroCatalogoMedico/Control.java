package CitaMedica.MicroCatalogoMedico;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

    @Autowired
    private Servicio servicio;

    @GetMapping("/buscar/{id}")
    public Medico buscar(@PathVariable Long id) {
        return servicio.buscar(id);
    }

    @GetMapping("/listarPorEspecialidad/{esp}")
    public List<Medico> listarPorEspecialidad(@PathVariable String esp) {
        return servicio.listarPorEspecialidad(esp);
    }

    @GetMapping("/listarEspecialidades")
    public List<String> listarEspecialidades() {
        return servicio.listarEspecialidades();
    }

    @GetMapping("/listar")
    public List<Medico> listar() {
        return servicio.listar();
    }
}