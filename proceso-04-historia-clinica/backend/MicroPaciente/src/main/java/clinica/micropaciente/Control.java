package clinica.micropaciente;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

    @Autowired
    private Servicio servicio;

    // Endpoint para Listar Todos los Pacientes
    @GetMapping("/pacientes/listar")
    public List<Paciente> listarPacientes() {
        return servicio.listarPacientes();
    }

    // Endpoint para Buscar Paciente por ID
    @GetMapping("/pacientes/{id}")
    public Paciente buscarPacientePorId(@PathVariable Long id) {
        return servicio.buscarPacientePorId(id);
    }

    // Endpoint alternativo compatible para buscar por ID
    @GetMapping("/pacientes/buscar/{id}")
    public Paciente buscarPacientePorIdAlias(@PathVariable Long id) {
        return servicio.buscarPacientePorId(id);
    }

    // Endpoint para Buscar Paciente por DNI (Verificar si ya existe o es primera cita)
    @GetMapping("/pacientes/dni/{dni}")
    public Paciente buscarPacientePorDni(@PathVariable String dni) {
        return servicio.buscarPacientePorDni(dni);
    }

    // Endpoint para Registrar Nuevo Paciente
    @PostMapping("/pacientes/grabar")
    public Paciente registrarPaciente(@RequestBody Paciente paciente) {
        return servicio.registrarPaciente(paciente);
    }

    // Endpoint para Actualizar Datos de Paciente por ID
    @PutMapping("/pacientes/{id}")
    public Paciente actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        return servicio.actualizarPaciente(id, paciente);
    }

    // Endpoint para Eliminar Paciente por ID
    @DeleteMapping("/pacientes/{id}")
    public void eliminarPaciente(@PathVariable Long id) {
        servicio.eliminarPaciente(id);
    }
}
