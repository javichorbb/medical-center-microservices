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
    public AtencionMedica grabar(@RequestBody AtencionMedica atencion) {
        return servicio.grabar(atencion);
    }

    @GetMapping("/buscar/{idAtencion}")
    public AtencionMedica buscar(@PathVariable Long idAtencion) {
        return servicio.buscar(idAtencion);
    }

    @GetMapping("/listar")
    public List<AtencionMedica> listar() {
        return servicio.listar();
    }

    @PostMapping("/grabar-completa")
    public ResultadoAtencion grabarCompleta(@RequestBody EntradaAtencion dto) {
        try {
            return servicio.grabarCompleta(dto);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping("/buscar-completa/{idAtencion}")
    public ResultadoAtencion buscarCompleta(@PathVariable Long idAtencion) {
        return servicio.buscarCompleta(idAtencion);
    }

    @GetMapping("/historia/{idHistoria}")
    public List<ResultadoAtencion> buscarPorHistoria(@PathVariable Long idHistoria) {
        return servicio.buscarPorHistoria(idHistoria);
    }

    @GetMapping("/cita/{idCita}")
    public ResultadoAtencion buscarPorCita(@PathVariable Long idCita) {
        return servicio.buscarPorCita(idCita);
    }

    @GetMapping("/listar-completas")
    public List<ResultadoAtencion> listarCompletas() {
        return servicio.listarCompletas();
    }
}
