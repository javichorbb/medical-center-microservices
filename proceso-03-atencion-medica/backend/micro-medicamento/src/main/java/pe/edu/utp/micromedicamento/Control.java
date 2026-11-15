package pe.edu.utp.micromedicamento;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicamento")
public class Control {

    @Autowired
    private Servicio servicio;

    @PostMapping("/grabar")
    public Medicamento grabar(@RequestBody Medicamento medicamento) {
        return servicio.grabar(medicamento);
    }

    @GetMapping("/buscar/{idMedicamento}")
    public Medicamento buscar(@PathVariable Long idMedicamento) {
        return servicio.buscar(idMedicamento);
    }

    @GetMapping("/listar")
    public List<Medicamento> listar() {
        return servicio.listar();
    }
}
