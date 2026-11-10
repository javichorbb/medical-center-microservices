
package Clinica.VistaRegistroCita;

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

    // Muestra el formulario
    @GetMapping("/verGrabar")
    public String verGrabar() {
        return "grabarCita";
    }

    // Procesa el formulario y llama al backend
    @PostMapping("/grabar")
    public String grabar(
            @RequestParam(name = "idHorario") Long idHorario,
            @RequestParam(name = "idPaciente") Long idPaciente,
            Model m) {

        CitaMedica cita = new CitaMedica();
        cita.setIdHorario(idHorario);
        cita.setIdPaciente(idPaciente);

        CitaMedica nueva = s.grabar(cita);

        if (nueva != null) {
            m.addAttribute("cita", nueva);
            m.addAttribute("exito", true);
        } else {
            m.addAttribute("exito", false);
        }

        return "grabarCita";
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/verGrabar";
    }
}