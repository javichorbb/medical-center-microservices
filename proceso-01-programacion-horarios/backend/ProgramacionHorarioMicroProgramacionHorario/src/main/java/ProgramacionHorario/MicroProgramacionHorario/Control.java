package ProgramacionHorario.MicroProgramacionHorario;

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

    // Endpoint para Registrar Nueva Programación de Horario (Devuelve la programación con ID generado)
    @PostMapping("/programaciones/grabar")
    public ProgramacionHorario registrarProgramacion(@RequestBody ProgramacionHorario p) {
        return servicio.registrarProgramacion(p);
    }

    // Endpoint para Listar Todas las Programaciones
    @GetMapping("/programaciones/listar")
    public List<ProgramacionHorario> listarProgramaciones() {
        return servicio.listarProgramaciones();
    }

    // Endpoint para Buscar Programación por ID
    @GetMapping("/programaciones/{id}")
    public ProgramacionHorario buscarProgramacionPorId(@PathVariable Long id) {
        return servicio.buscarProgramacionPorId(id);
    }

    // Endpoint para Listar Programaciones por Médico
    @GetMapping("/programaciones/medico/{idMedico}")
    public List<ProgramacionHorario> listarProgramacionesPorMedico(@PathVariable Long idMedico) {
        return servicio.listarProgramacionesPorMedico(idMedico);
    }

    // Endpoint para Actualizar Programación por ID
    @PutMapping("/programaciones/{id}")
    public ProgramacionHorario actualizarProgramacion(@PathVariable Long id, @RequestBody ProgramacionHorario p) {
        return servicio.actualizarProgramacion(id, p);
    }

    // Endpoint para Eliminar Programación por ID
    @DeleteMapping("/programaciones/{id}")
    public void eliminarProgramacion(@PathVariable Long id) {
        servicio.eliminarProgramacion(id);
    }
}
