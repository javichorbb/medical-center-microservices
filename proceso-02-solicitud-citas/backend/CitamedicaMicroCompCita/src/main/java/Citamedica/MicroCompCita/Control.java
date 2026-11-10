package Citamedica.MicroCompCita;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

    @Autowired
    private Servicio servicio;

    // Endpoint Paso 1: Consultar Especialidades Médicas
    @GetMapping("/especialidades")
    public List<String> listarEspecialidades() {
        return servicio.listarEspecialidades();
    }

    // Endpoint Paso 2: Consultar Médicos por Especialidad
    @GetMapping("/medicos/{esp}")
    public List<Medico> listarMedicosPorEspecialidad(@PathVariable String esp) {
        return servicio.listarMedicosPorEspecialidad(esp);
    }

    // Endpoint Paso 3: Consultar Ficha del Médico (Datos del Médico + Lista de sus Horarios)
    @PostMapping("/consultarFichaMedico")
    public SalidaMedico consultarFichaMedico(@RequestBody EntradaMedico ent) {
        return servicio.consultarFichaMedico(ent);
    }

    // Endpoint Adicional Paso 3: Consultar Solo Horarios Disponibles del Médico
    @GetMapping("/horariosDisponibles/{idMedico}")
    public List<Horario> listarHorariosDisponibles(@PathVariable Long idMedico) {
        return servicio.listarHorariosDisponibles(idMedico);
    }

    // Endpoint Paso 4: Validar Paciente por DNI
    @PostMapping("/validarPaciente")
    public Paciente validarPaciente(@RequestBody EntradaPaciente ent) {
        return servicio.validarPaciente(ent != null ? ent.getDni() : null);
    }

    // Endpoint Paso 5: Registrar Cita Médica (Genera Comprobante Detallado con Médico, Paciente y Horario)
    @PostMapping("/registrarCita")
    public SalidaCita registrarCita(@RequestBody EntradaCita ent) {
        return servicio.registrarCita(ent);
    }
}