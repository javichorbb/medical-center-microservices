package pe.edu.utp.microcestaanalisis.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.microcestaanalisis.dto.AnalisisCestaDTO;
import pe.edu.utp.microcestaanalisis.servicio.CestaAnalisisService;

@RestController
@RequestMapping("/cesta-analisis")
public class CestaAnalisisController {

    @Autowired
    private CestaAnalisisService servicio;

    // Agregar o actualizar un analisis
    @PostMapping("/{operacionId}/agregar")
    public AnalisisCestaDTO agregar(
            @PathVariable String operacionId,
            @RequestBody AnalisisCestaDTO item) {

        return servicio.agregar(operacionId, item);
    }

    // Quitar un analisis
    @DeleteMapping("/{operacionId}/quitar/{idAnalisis}")
    public Map<String, Object> quitar(
            @PathVariable String operacionId,
            @PathVariable Long idAnalisis) {

        boolean eliminado =
            servicio.quitar(operacionId, idAnalisis);

        return Map.of(
            "eliminado", eliminado,
            "mensaje", eliminado
                ? "Analisis retirado"
                : "Analisis no encontrado");
    }

    // Listar los analisis
    @GetMapping("/{operacionId}/listar")
    public List<AnalisisCestaDTO> listar(
            @PathVariable String operacionId) {

        return servicio.listar(operacionId);
    }

    // Vaciar la cesta
    @DeleteMapping("/{operacionId}/limpiar")
    public Map<String, String> limpiar(
            @PathVariable String operacionId) {

        servicio.limpiar(operacionId);

        return Map.of(
            "mensaje", "Cesta de analisis vaciada");
    }

    // Manejar errores de validacion
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarError(
            IllegalArgumentException ex) {

        return Map.of("error", ex.getMessage());
    }
}
