package pe.edu.utp.microhistoria.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.microhistoria.entidad.HistoriaClinica;
import pe.edu.utp.microhistoria.servicio.HistoriaClinicaService;

@RestController
@RequestMapping("/historia")
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaService servicio;

    @PostMapping("/grabar")
    public HistoriaClinica grabar(@RequestBody HistoriaClinica historia) {
        return servicio.grabar(historia);
    }

    @GetMapping("/buscar/{idHistoria}")
    public HistoriaClinica buscar(@PathVariable Long idHistoria) {
        return servicio.buscar(idHistoria);
    }

    @GetMapping("/paciente/{idPaciente}")
    public HistoriaClinica buscarPorPaciente(@PathVariable Long idPaciente) {
        return servicio.buscarPorPaciente(idPaciente);
    }

    @GetMapping("/listar")
    public List<HistoriaClinica> listar() {
        return servicio.listar();
    }
}
