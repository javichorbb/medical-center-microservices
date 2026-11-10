package Citamedica.VistaCitaMedica;

import java.util.List;
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

    // Carga de la vista principal con navegación por pasos
    @GetMapping("/citas")
    public String verCitas(
            @RequestParam(name = "especialidad", required = false) String especialidad,
            @RequestParam(name = "idMedico", required = false) Long idMedico,
            @RequestParam(name = "idHorario", required = false) Long idHorario,
            Model m) {

        // Paso 1: Cargar siempre especialidades
        List<String> especialidades = s.listarEspecialidades();
        m.addAttribute("especialidades", especialidades);
        m.addAttribute("especialidadSeleccionada", especialidad);

        // Paso 2: Si eligió especialidad, cargar médicos especialistas
        if (especialidad != null && !especialidad.trim().isEmpty()) {
            List<Medico> medicos = s.listarMedicosPorEspecialidad(especialidad);
            m.addAttribute("medicos", medicos);
        }

        // Paso 3: Si seleccionó médico, cargar sus horarios
        Horario horarioElegido = null;
        if (idMedico != null) {
            SalidaMedico ficha = s.consultarFichaMedico(idMedico);
            m.addAttribute("ficha", ficha);
            m.addAttribute("idMedicoSeleccionado", idMedico);

            // Si además pulsó "Seleccionar" en un horario, buscar los datos de ese horario
            if (idHorario != null && ficha != null && ficha.getHorarios() != null) {
                for (Horario h : ficha.getHorarios()) {
                    if (h.getIdHorario() != null && h.getIdHorario().equals(idHorario)) {
                        horarioElegido = h;
                        break;
                    }
                }
            }
        }

        // Paso 4: Si hay un horario seleccionado, pasarlo al modelo para activar el paso de validación
        m.addAttribute("horarioSeleccionado", horarioElegido);
        m.addAttribute("idHorarioSeleccionado", idHorario);

        return "citaMedica";
    }

    // Paso 4: Procesar Validación de Paciente
    @PostMapping("/validarPaciente")
    public String validarPaciente(
            @RequestParam(name = "especialidad") String especialidad,
            @RequestParam(name = "idMedico") Long idMedico,
            @RequestParam(name = "idHorario") Long idHorario,
            @RequestParam(name = "dni") String dni,
            Model m) {

        Paciente pac = s.validarPaciente(dni);

        if (pac != null) {
            m.addAttribute("pacienteValidado", pac);
        } else {
            m.addAttribute("errorValidacion", "El DNI \"" + dni + "\" no pertenece a un paciente registrado en el sistema.");
        }

        return verCitas(especialidad, idMedico, idHorario, m);
    }

    // Paso 5: Confirmar y Registrar la Cita Médica
    @PostMapping("/confirmarCita")
    public String confirmarCita(
            @RequestParam(name = "idHorario") Long idHorario,
            @RequestParam(name = "idPaciente") Long idPaciente,
            @RequestParam(name = "especialidad") String especialidad,
            @RequestParam(name = "idMedico") Long idMedico,
            Model m) {

        SalidaCita comprobante = s.registrarCita(idHorario, idPaciente);

        if (comprobante != null) {
            m.addAttribute("comprobanteCita", comprobante);
        } else {
            m.addAttribute("errorRegistro", "Ocurrió un error al registrar la cita médica.");
        }

        return verCitas(especialidad, idMedico, idHorario, m);
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/citas";
    }
}