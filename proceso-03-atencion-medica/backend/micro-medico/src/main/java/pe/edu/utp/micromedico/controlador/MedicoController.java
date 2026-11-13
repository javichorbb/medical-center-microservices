package pe.edu.utp.micromedico.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.micromedico.entidad.Medico;
import pe.edu.utp.micromedico.servicio.MedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService servicio;

    @PostMapping("/grabar")
    public Medico grabar(@RequestBody Medico medico) {
        return servicio.grabar(medico);
    }

    @GetMapping("/buscar/{idMedico}")
    public Medico buscar(@PathVariable Long idMedico) {
        return servicio.buscar(idMedico);
    }

    @GetMapping("/listar")
    public List<Medico> listar() {
        return servicio.listar();
    }
}
