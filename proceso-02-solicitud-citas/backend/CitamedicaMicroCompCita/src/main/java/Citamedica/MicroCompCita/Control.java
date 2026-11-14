package Citamedica.MicroCompCita;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cita-compuesto")
public class Control {

    @Autowired
    private Servicio servicio;

    // Paso 1: Consultar Especialidades Médicas
    @GetMapping("/medicos/especialidades")
    public List<String> listarEspecialidades() {
        return servicio.listarEspecialidades();
    }

    // Paso 2: Consultar Médicos por Especialidad
    @GetMapping("/medicos/especialidad/{especialidad}")
    public List<Medico> listarMedicosPorEspecialidad(@PathVariable String especialidad) {
        return servicio.listarMedicosPorEspecialidad(especialidad);
    }

    // Paso 3: Consultar Ficha del Médico (Datos del Médico + Lista de sus Horarios)
    @GetMapping("/medicos/{idMedico}")
    public SalidaMedico consultarFichaMedico(@PathVariable Long idMedico) {
        return servicio.consultarFichaMedico(idMedico);
    }

    // Paso 3 (Adicional): Consultar Solo Horarios Disponibles del Médico
    @GetMapping("/horarios/disponibles/{idMedico}")
    public List<Horario> listarHorariosDisponibles(@PathVariable Long idMedico) {
        return servicio.listarHorariosDisponibles(idMedico);
    }

    // Paso 4: Validar Paciente por DNI
    @PostMapping("/validarPaciente")
    public Paciente validarPaciente(@RequestBody EntradaPaciente ent) {
        return servicio.validarPaciente(ent != null ? ent.getDni() : null);
    }

    // Paso 5: Grabar Cita Médica (Genera Comprobante Detallado con Médico, Paciente y Horario)
    @PostMapping("/citas/grabar")
    public SalidaCita registrarCita(@RequestBody EntradaCita ent) {
        return servicio.registrarCita(ent);
    }

    // Consultar Cita Médica Detallada por su ID
    @GetMapping("/citas/{idCita}")
    public SalidaCita buscarCita(@PathVariable Long idCita) {
        return servicio.buscarCita(idCita);
    }

    // Consultar Citas Médicas Detalladas de un Paciente por DNI
    @GetMapping("/citas/dni/{dni}")
    public List<SalidaCita> buscarCitasPorDni(@PathVariable String dni) {
        return servicio.buscarCitasPorDni(dni);
    }
}
