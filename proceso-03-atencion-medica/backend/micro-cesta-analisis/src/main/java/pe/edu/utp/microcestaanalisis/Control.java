package pe.edu.utp.microcestaanalisis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cesta-analisis")
public class Control {

    @Autowired
    private Servicio servicio;

    @PostMapping("/agregar")
    public List<AnalisisCesta> agregar(@RequestBody AnalisisCesta item) {
        return servicio.agregar(item);
    }

    @DeleteMapping("/quitar/{idAnalisis}")
    public List<AnalisisCesta> quitar(@PathVariable Long idAnalisis) {
        return servicio.quitar(idAnalisis);
    }

    @GetMapping("/listar")
    public List<AnalisisCesta> listar() {
        return servicio.listar();
    }

    @DeleteMapping("/limpiar")
    public void limpiar() {
        servicio.limpiar();
    }
}
