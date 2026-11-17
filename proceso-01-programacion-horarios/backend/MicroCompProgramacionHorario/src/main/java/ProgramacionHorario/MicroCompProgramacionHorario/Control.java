package ProgramacionHorario.MicroCompProgramacionHorario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programacion-compuesto")
public class Control {

    @Autowired
    private Servicio servicio;

    // 1. Grabar Programación de Horario (recibe Entrada y devuelve Salida consolidada)
    @PostMapping("/grabar")
    public Salida grabarProgramacion(@RequestBody Entrada ent) {
        return servicio.grabarProgramacion(ent);
    }

    // 2. Consultar Programación de Horario por ID (devuelve Salida consolidada con sus franjas)
    @GetMapping("/buscar/{id}")
    public Salida verSalida(@PathVariable Long id) {
        return servicio.verSalida(id);
    }

    // 3. Consultar Programaciones de Horarios por Especialidad Médica
    @GetMapping("/especialidad/{especialidad}")
    public List<Salida> listarPorEspecialidad(@PathVariable String especialidad) {
        return servicio.listarPorEspecialidad(especialidad);
    }

    // 4. Listar Todas las Programaciones de Horarios del Sistema
    @GetMapping("/listar")
    public List<Salida> listarTodas() {
        return servicio.listarTodas();
    }
}
