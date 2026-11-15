package pe.edu.utp.microcestaanalisis;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cesta-analisis")
public class Control {

    @Autowired
    private Servicio servicio;

    @PostMapping("/{operacionId}/agregar")
    public AnalisisCesta agregar(
            @PathVariable String operacionId,
            @RequestBody AnalisisCesta item) {
        return servicio.agregar(operacionId, item);
    }

    @DeleteMapping("/{operacionId}/quitar/{idAnalisis}")
    public Map<String, Object> quitar(
            @PathVariable String operacionId,
            @PathVariable Long idAnalisis) {
        boolean eliminado = servicio.quitar(operacionId, idAnalisis);
        return Map.of(
                "eliminado", eliminado,
                "mensaje", eliminado ? "Analisis retirado" : "Analisis no encontrado");
    }

    @GetMapping("/{operacionId}/listar")
    public List<AnalisisCesta> listar(@PathVariable String operacionId) {
        return servicio.listar(operacionId);
    }

    @DeleteMapping("/{operacionId}/limpiar")
    public Map<String, String> limpiar(@PathVariable String operacionId) {
        servicio.limpiar(operacionId);
        return Map.of("mensaje", "Cesta de analisis vaciada");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarError(IllegalArgumentException ex) {
        return Map.of("error", ex.getMessage());
    }
}
