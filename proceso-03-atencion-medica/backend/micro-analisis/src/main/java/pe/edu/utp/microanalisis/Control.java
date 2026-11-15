package pe.edu.utp.microanalisis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analisis")
public class Control {

    @Autowired
    private Servicio servicio;

    @PostMapping("/grabar")
    public AnalisisClinico grabar(@RequestBody AnalisisClinico analisis) {
        return servicio.grabar(analisis);
    }

    @GetMapping("/buscar/{idAnalisis}")
    public AnalisisClinico buscar(@PathVariable Long idAnalisis) {
        return servicio.buscar(idAnalisis);
    }

    @GetMapping("/listar")
    public List<AnalisisClinico> listar() {
        return servicio.listar();
    }
}
