package ProgramacionHorario.MicroFranjaHoraria;

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

    // Endpoint para Registrar Nueva Franja Horaria (Valida máx 20 minutos)
    @PostMapping("/franjas/grabar")
    public FranjaHoraria registrarFranja(@RequestBody FranjaHoraria f) {
        return servicio.registrarFranja(f);
    }

    // Endpoint para Listar Todas las Franjas Horarias
    @GetMapping("/franjas/listar")
    public List<FranjaHoraria> listarFranjas() {
        return servicio.listarFranjas();
    }

    // Endpoint para Buscar Franja Horaria por ID
    @GetMapping("/franjas/{id}")
    public FranjaHoraria buscarFranjaPorId(@PathVariable Long id) {
        return servicio.buscarFranjaPorId(id);
    }

    // Endpoint para Listar Franjas Horarias por ID de Programación
    @GetMapping("/franjas/programacion/{idProgramacion}")
    public List<FranjaHoraria> listarFranjasPorProgramacion(@PathVariable Long idProgramacion) {
        return servicio.listarFranjasPorProgramacion(idProgramacion);
    }

    // Endpoint para Actualizar Franja Horaria por ID (Valida máx 20 minutos)
    @PutMapping("/franjas/{id}")
    public FranjaHoraria actualizarFranja(@PathVariable Long id, @RequestBody FranjaHoraria f) {
        return servicio.actualizarFranja(id, f);
    }

    // Endpoint para Eliminar Franja Horaria por ID
    @DeleteMapping("/franjas/{id}")
    public void eliminarFranja(@PathVariable Long id) {
        servicio.eliminarFranja(id);
    }
}
