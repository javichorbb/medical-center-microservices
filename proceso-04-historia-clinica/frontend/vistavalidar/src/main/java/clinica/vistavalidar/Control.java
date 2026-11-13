package clinica.vistavalidar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/validar")
public class Control {

    @Autowired
    private Servicio servicio;

    @GetMapping("/login")
    public String verValidar(Model model) {
        model.addAttribute("enfermera", new Enfermera());
        return "login";
    }

    @PostMapping("/procesar")
    public String validar(@ModelAttribute Enfermera enfermera, Model model) {
        Enfermera encontrada = servicio.validar(enfermera);
        if (encontrada != null) {
            model.addAttribute("enfermera", encontrada);
            return "validar";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            model.addAttribute("enfermera", new Enfermera());
            return "login";
        }
    }
}