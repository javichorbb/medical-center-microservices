package ProgramacionHorario.VistaValidar;

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
        return "login";
    }

    @PostMapping("/validar")
    public String validar(Entrada ent, Model model) {
        Usuario u = s.validar(ent);

        if (u != null) {
            model.addAttribute("usuario", u);
            return "bienvenido";
        }

        model.addAttribute("error", "ID o contraseña incorrectos");
        return "login";
    }

    @GetMapping("/verGrabar")
    public String verGrabar(Model model) {
        model.addAttribute("usu", new Usuario());
        return "grabar";
    }

    @PostMapping("/grabar")
    public String grabar(Usuario u) {
        s.grabar(u);
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
        model.addAttribute("usu", s.buscar(id));
        return "editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, Usuario u) {
        s.actualizar(id, u);
        return "redirect:/listar";
    }
}
