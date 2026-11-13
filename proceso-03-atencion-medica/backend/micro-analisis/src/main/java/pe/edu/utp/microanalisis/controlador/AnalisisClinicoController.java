package pe.edu.utp.microanalisis.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.microanalisis.entidad.AnalisisClinico;
import pe.edu.utp.microanalisis.servicio.AnalisisClinicoService;

@RestController
@RequestMapping("/analisis")
public class AnalisisClinicoController {

    @Autowired
    private AnalisisClinicoService servicio;

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
