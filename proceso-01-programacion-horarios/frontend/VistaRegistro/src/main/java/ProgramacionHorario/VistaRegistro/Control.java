package ProgramacionHorario.VistaRegistro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Control {

    @Autowired
    private Servicio s;

    @GetMapping("/")
    public String inicio() {
        return "redirect:/listar";
    }

    @GetMapping("/verGrabar")
    public String verGrabar(Model m) {
        m.addAttribute("pro", new ProgramacionHorario());
        return "grabar";
    }

    @PostMapping("/grabar")
    public String grabar(ProgramacionHorario p) {
        s.grabar(p);
        return "redirect:/listar";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("resultado", s.buscar(id));
        }
        return "buscar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("lista", s.listar());
        return "listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        s.eliminar(id);
        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String verEditar(@PathVariable Long id, Model model) {
        model.addAttribute("pro", s.buscar(id));
        return "editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, ProgramacionHorario p) {
        s.actualizar(id, p);
        return "redirect:/listar";
    }
}
