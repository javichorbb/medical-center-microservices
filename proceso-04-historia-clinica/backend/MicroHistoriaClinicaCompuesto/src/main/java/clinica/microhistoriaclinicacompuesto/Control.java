package clinica.microhistoriaclinicacompuesto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historia-compuesto")
public class Control {

    @Autowired
    private Servicio servicio;

    // 1. Autenticación / Login de Enfermera (Mismo comportamiento que Proceso 1)
    @PostMapping("/validarEnfermera")
    public SalidaEnfermera validarEnfermera(@RequestBody EntradaEnfermera ent) {
        return servicio.validarEnfermera(ent);
    }

    // 2. Registrar Paciente en su Primera Cita y Generar su Historia Clínica
    @PostMapping("/registrarPaciente")
    public SalidaHistoria registrarPaciente(@RequestBody EntradaPaciente ent) {
        return servicio.registrarPaciente(ent);
    }

    // 3. Consultar Historia Clínica Completa por DNI del Paciente
    @GetMapping("/dni/{dni}")
    public SalidaHistoria verHistoriaPorDni(@PathVariable String dni) {
        return servicio.obtenerHistoriaPorDni(dni);
    }

    // 4. Consultar Historia Clínica Completa por ID de Paciente
    @GetMapping("/paciente/{idPaciente}")
    public SalidaHistoria verHistoriaPorPaciente(@PathVariable Long idPaciente) {
        return servicio.obtenerHistoriaPorPaciente(idPaciente);
    }
}
