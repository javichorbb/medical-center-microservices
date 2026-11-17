package pe.edu.utp.microatencion;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/atencion")
public class Control {

    @Autowired
    private Servicio servicio;

    @PostMapping("/grabar")
    public ResultadoAtencion grabar(@RequestBody EntradaAtencion dto) {
        try {
            return servicio.grabar(dto);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping("/buscar/{idAtencion}")
    public ResultadoAtencion buscar(@PathVariable Long idAtencion) {
        return servicio.buscar(idAtencion);
    }

    @GetMapping("/historia/{idHistoria}")
    public List<ResultadoAtencion> buscarPorHistoria(@PathVariable Long idHistoria) {
        return servicio.buscarPorHistoria(idHistoria);
    }

    @GetMapping("/listar")
    public List<ResultadoAtencion> listar() {
        return servicio.listar();
    }
}
