package clinica.vistahistoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/historia")
public class Control {

    @Autowired
    private Servicio servicio;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("historias", servicio.listar());
        return "listar";
    }

    @GetMapping("/verGrabar")
    public String verGrabar(Model model) {
        model.addAttribute("historia", new Historia());
        return "grabar";
    }

    @PostMapping("/grabar")
    public String registrar(@ModelAttribute Historia historia) {
        servicio.registrar(historia);
        return "redirect:/historia/listar";
    }

    @GetMapping("/buscar")
    public String verBuscar() {
        return "buscar";
    }

    @PostMapping("/buscarPorPaciente")
    public String buscarPorPaciente(@RequestParam String idPaciente, Model model) {
        model.addAttribute("historias", servicio.buscarPorPaciente(idPaciente));
        model.addAttribute("idPacienteBuscado", idPaciente);
        return "buscar";
    }
}