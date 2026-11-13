package pe.edu.utp.micromedicamento.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.micromedicamento.entidad.Medicamento;
import pe.edu.utp.micromedicamento.servicio.MedicamentoService;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {

    @Autowired
    private MedicamentoService servicio;

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
