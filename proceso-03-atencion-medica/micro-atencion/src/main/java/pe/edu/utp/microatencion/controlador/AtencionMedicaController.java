package pe.edu.utp.microatencion.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import pe.edu.utp.microatencion.entidad.AtencionMedica;
import pe.edu.utp.microatencion.dto.RegistroAtencionDTO;
import pe.edu.utp.microatencion.dto.ResultadoAtencionDTO;
import pe.edu.utp.microatencion.servicio.AtencionMedicaService;

@RestController
@RequestMapping("/atencion")
public class AtencionMedicaController {

    @Autowired
    private AtencionMedicaService servicio;

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
    public ResultadoAtencionDTO grabarCompleta(@RequestBody RegistroAtencionDTO dto) {
        try {
            return servicio.grabarCompleta(dto);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping("/buscar-completa/{idAtencion}")
    public ResultadoAtencionDTO buscarCompleta(@PathVariable Long idAtencion) {
        return servicio.buscarCompleta(idAtencion);
    }

    // Endpoint clave para el Proceso 4: Listar todas las atenciones de una Historia Clínica
    @GetMapping("/historia/{idHistoria}")
    public List<ResultadoAtencionDTO> buscarPorHistoria(@PathVariable Long idHistoria) {
        return servicio.buscarPorHistoria(idHistoria);
    }

    @GetMapping("/cita/{idCita}")
    public ResultadoAtencionDTO buscarPorCita(@PathVariable Long idCita) {
        return servicio.buscarPorCita(idCita);
    }

    @GetMapping("/listar-completas")
    public List<ResultadoAtencionDTO> listarCompletas() {
        return servicio.listarCompletas();
    }
}
