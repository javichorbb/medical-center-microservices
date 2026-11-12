package ProgramacionHorario.VistaGestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Control {

    @Autowired
    private Servicio s;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("entrada", new Entrada());
        return "login";
    }

    @PostMapping("/validar")
    public String validar(Entrada ent, Model model) {
        Usuario u = s.validar(ent);

        if (u != null) {
            model.addAttribute("usuario", u);
            return "redirect:/inicio";
        }

        model.addAttribute("error", "ID o contraseña incorrectos");
        model.addAttribute("entrada", ent);
        return "login";
    }

    @GetMapping("/inicio")
    public String menu(Model model) {
        return "inicio";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", s.listarUsuarios());
        return "usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario-form";
    }

    @PostMapping("/usuarios/grabar")
    public String grabarUsuario(Usuario u) {
        s.grabarUsuario(u);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", s.buscarUsuario(id));
        return "usuario-form";
    }

    @PostMapping("/usuarios/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Long id, Usuario u) {
        s.actualizarUsuario(id, u);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        s.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/medicos")
    public String medicos(Model model) {
        model.addAttribute("lista", s.listarMedicos());
        return "medicos";
    }

    @GetMapping("/medicos/nuevo")
    public String nuevoMedico(Model model) {
        model.addAttribute("medico", new Medico());
        return "medico-form";
    }

    @PostMapping("/medicos/grabar")
    public String grabarMedico(Medico m) {
        s.grabarMedico(m);
        return "redirect:/medicos";
    }

    @GetMapping("/medicos/editar/{id}")
    public String editarMedico(@PathVariable Long id, Model model) {
        model.addAttribute("medico", s.buscarMedico(id));
        return "medico-form";
    }

    @PostMapping("/medicos/actualizar/{id}")
    public String actualizarMedico(@PathVariable Long id, Medico m) {
        s.actualizarMedico(id, m);
        return "redirect:/medicos";
    }

    @GetMapping("/medicos/eliminar/{id}")
    public String eliminarMedico(@PathVariable Long id) {
        s.eliminarMedico(id);
        return "redirect:/medicos";
    }

    @GetMapping("/programacion")
    public String programacion(Model model) {
        model.addAttribute("medicos", s.listarMedicos());
        model.addAttribute("programaciones", s.listarProgramaciones());
        model.addAttribute("programacion", new ProgramacionHorario());
        return "programacion";
    }

    @GetMapping("/programacion/editar/{id}")
    public String editarProgramacion(@PathVariable Long id, Model model) {
        model.addAttribute("medicos", s.listarMedicos());
        model.addAttribute("programaciones", s.listarProgramaciones());
        model.addAttribute("programacion", s.buscarProgramacion(id));
        return "programacion";
    }

    @PostMapping("/programacion/grabar")
    public String grabarProgramacion(ProgramacionHorario p) {
        s.grabarProgramacion(p);
        return "redirect:/programacion";
    }

    @PostMapping("/programacion/actualizar/{id}")
    public String actualizarProgramacion(@PathVariable Long id, ProgramacionHorario p) {
        s.actualizarProgramacion(id, p);
        return "redirect:/programacion";
    }

    @GetMapping("/programacion/eliminar/{id}")
    public String eliminarProgramacion(@PathVariable Long id) {
        s.eliminarProgramacion(id);
        return "redirect:/programacion";
    }

    @GetMapping("/franjas")
    public String listarFranjas(Model model) {
        model.addAttribute("franjas", s.listarFranjas());
        model.addAttribute("programaciones", s.listarProgramaciones());
        return "franjas";
    }

    @PostMapping("/franja/grabar")
    public String grabarFranja(@ModelAttribute FranjaHoraria f) {
        s.grabarFranja(f);
        return "redirect:/franjas";
    }

    @GetMapping("/franja/editar/{id}")
    public String editarFranja(@PathVariable Long id, Model model) {
        model.addAttribute("franja", s.buscarFranja(id));
        model.addAttribute("programaciones", s.listarProgramaciones());
        return "franja-form";
    }

    @PostMapping("/franja/actualizar/{id}")
    public String actualizarFranja(
            @PathVariable Long id,
            @ModelAttribute FranjaHoraria f) {
        s.actualizarFranja(id, f);
        return "redirect:/franjas";
    }

    @GetMapping("/franja/eliminar/{id}")
    public String eliminarFranja(@PathVariable Long id) {
        s.eliminarFranja(id);
        return "redirect:/franjas";
    }

    @GetMapping("/franja/nueva")
    public String nuevaFranja(Model model) {
        model.addAttribute("franja", new FranjaHoraria());
        model.addAttribute("programaciones", s.listarProgramaciones());
        return "franja-form";
    }
}
