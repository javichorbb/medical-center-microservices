
package Clinica.VistaCatalogoHorario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Control {

    @Autowired
    private Servicio s;

    @GetMapping("/verHorarios")
    public String verHorarios(
            @RequestParam(name = "idMedico", required = false) Long idMedico,
            Model m) {

        if (idMedico != null) {
            List<Horario> horarios = s.listarDisponibles(idMedico);
            m.addAttribute("horarios", horarios);
            m.addAttribute("idMedico", idMedico);
            m.addAttribute("busquedaRealizada", true);
        } else {
            m.addAttribute("busquedaRealizada", false);
        }

        return "listarHorarios";
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/verHorarios";
    }
}