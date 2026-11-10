package Clinica.VistaCatalogoMedico;

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

    @GetMapping("/verMedicos")
    public String verMedicos(
            @RequestParam(name = "especialidad", required = false) String especialidad,
            Model m) {

        // 1. Siempre cargamos la lista de especialidades de @GetMapping("/especialidades")
        List<String> especialidades = s.listarEspecialidades();
        m.addAttribute("especialidades", especialidades);

        // 2. Si el usuario ingresó o seleccionó una especialidad, buscamos los médicos
        if (especialidad != null && !especialidad.trim().isEmpty()) {
            List<Medico> medicos = s.listarPorEspecialidad(especialidad);
            m.addAttribute("medicos", medicos);
            m.addAttribute("especialidad", especialidad);
            m.addAttribute("busquedaRealizada", true);
        } else {
            m.addAttribute("busquedaRealizada", false);
        }

        return "listarMedicos";
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/verMedicos";
    }
}
