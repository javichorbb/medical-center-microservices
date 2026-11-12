package ProgramacionHorario.MicroMedico;

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

    // Endpoint para Registrar Nuevo Médico
    @PostMapping("/medicos/grabar")
    public void registrarMedico(@RequestBody Medico m) {
        servicio.registrarMedico(m);
    }

    // Endpoint para Listar Todos los Médicos
    @GetMapping("/medicos/listar")
    public List<Medico> listarMedicos() {
        return servicio.listarMedicos();
    }

    // Endpoint para Listar Todas las Especialidades Disponibles
    @GetMapping("/medicos/especialidades")
    public List<String> listarEspecialidades() {
        return servicio.listarEspecialidades();
    }

    // Endpoint para Buscar Médico por ID
    @GetMapping("/medicos/{id}")
    public Medico buscarMedicoPorId(@PathVariable Long id) {
        return servicio.buscarMedicoPorId(id);
    }

    // Endpoint para Buscar Médicos por Especialidad
    @GetMapping("/medicos/especialidad/{especialidad}")
    public List<Medico> listarMedicosPorEspecialidad(@PathVariable String especialidad) {
        return servicio.listarMedicosPorEspecialidad(especialidad);
    }

    // Endpoint para Actualizar Datos del Médico por ID
    @PutMapping("/medicos/{id}")
    public Medico actualizarMedico(@PathVariable Long id, @RequestBody Medico m) {
        return servicio.actualizarMedico(id, m);
    }

    // Endpoint para Eliminar Médico por ID
    @DeleteMapping("/medicos/{id}")
    public void eliminarMedico(@PathVariable Long id) {
        servicio.eliminarMedico(id);
    }
}
