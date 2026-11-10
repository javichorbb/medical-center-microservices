package Clinica.VistaValidar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Control {

    @Autowired
    private Servicio s;

    @GetMapping("/validar")
    public String verValidar() {
        return "validar";
    }

    @PostMapping("/validar")
    public String validar(
            @RequestParam(name = "dni") String dni,
            Model m) {

        // El idPaciente se envía como null, porque el backend solo usa el DNI
        EntradaPaciente entrada = new EntradaPaciente(null, dni);
        Paciente paciente = s.validar(entrada);

        m.addAttribute("dni", dni);

        if (paciente != null) {
            m.addAttribute("paciente", paciente);
            m.addAttribute("exito", true);
        } else {
            m.addAttribute("exito", false);
        }

        return "validar";
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/validar";
    }
}