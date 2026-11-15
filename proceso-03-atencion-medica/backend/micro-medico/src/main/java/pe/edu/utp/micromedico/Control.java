package pe.edu.utp.micromedico;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
public class Control {

    @Autowired
    private Servicio servicio;

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
