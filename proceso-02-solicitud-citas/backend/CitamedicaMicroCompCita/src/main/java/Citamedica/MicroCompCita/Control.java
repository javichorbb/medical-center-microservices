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

    // 1. Grabar Cita Médica (recibe Entrada y devuelve Salida consolidada)
    @PostMapping("/grabar")
    public Salida grabarCita(@RequestBody Entrada ent) {
        return servicio.grabarCita(ent);
    }

    // 2. Consultar Cita Médica por ID (devuelve Salida consolidada)
    @GetMapping("/buscar/{id}")
    public Salida verSalida(@PathVariable Long id) {
        return servicio.verSalida(id);
    }

    // 3. Listar Todas las Citas Médicas consolidadas
    @GetMapping("/listar")
    public List<Salida> listarTodas() {
        return servicio.listarTodas();
    }

    // 4. Consultas de apoyo de médicos y especialidades
    @GetMapping("/medicos/especialidades")
    public List<String> listarEspecialidades() {
        return servicio.listarEspecialidades();
    }

    @GetMapping("/medicos/especialidad/{especialidad}")
    public List<Medico> listarMedicosPorEspecialidad(@PathVariable String especialidad) {
        return servicio.listarMedicosPorEspecialidad(especialidad);
    }
}
