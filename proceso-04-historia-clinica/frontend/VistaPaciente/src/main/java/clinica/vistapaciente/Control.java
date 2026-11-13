package clinica.vistapaciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paciente")
public class Control {

    @Autowired
    private Servicio servicio;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("pacientes", servicio.listar());
        return "listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "grabar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Paciente paciente) {
        servicio.guardar(paciente);
        return "redirect:/paciente/listar";
    }

    @GetMapping("/buscar/{id}")
    public String buscarPorId(@PathVariable String id, Model model) {
        model.addAttribute("paciente", servicio.buscarPorId(id));
        return "buscar";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(name = "id", required = false) String id, Model model) {
        if (id != null && !id.trim().isEmpty()) {
            model.addAttribute("paciente", servicio.buscarPorId(id));
        }
        return "buscar";
    }

    // NUEVO: Cargar formulario de edición
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        model.addAttribute("paciente", servicio.buscarPorId(id));
        return "editar";
    }

    // NUEVO: Procesar la actualización
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Paciente paciente) {
        servicio.actualizar(paciente);
        return "redirect:/paciente/listar";
    }

    // NUEVO: Eliminar paciente
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        servicio.eliminar(id);
        return "redirect:/paciente/listar";
    }
}