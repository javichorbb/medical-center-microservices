package Clinica.microatencionmedica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/atencion")
public class Control {

    @Autowired
    private Servicio servicio;

    @GetMapping("/listar")
    public List<AtencionMedica> listar() {
        return servicio.listar();
    }

    @GetMapping("/buscar/{idAtencion}")
    public AtencionMedica buscar(@PathVariable Long idAtencion) {
        return servicio.buscar(idAtencion);
    }

    @GetMapping("/historia/{idHistoria}")
    public List<AtencionMedica> buscarPorHistoria(@PathVariable Long idHistoria) {
        return servicio.buscarPorHistoria(idHistoria);
    }
}
