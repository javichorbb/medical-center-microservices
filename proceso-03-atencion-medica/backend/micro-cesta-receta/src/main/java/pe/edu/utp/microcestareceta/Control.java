package pe.edu.utp.microcestareceta;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cesta-receta")
public class Control {

    @Autowired
    private Servicio servicio;

    @PostMapping("/agregar")
    public List<MedicamentoCesta> agregar(@RequestBody MedicamentoCesta item) {
        return servicio.agregar(item);
    }

    @DeleteMapping("/quitar/{idMedicamento}")
    public List<MedicamentoCesta> quitar(@PathVariable Long idMedicamento) {
        return servicio.quitar(idMedicamento);
    }

    @GetMapping("/listar")
    public List<MedicamentoCesta> listar() {
        return servicio.listar();
    }

    @DeleteMapping("/limpiar")
    public void limpiar() {
        servicio.limpiar();
    }
}
