package pe.edu.utp.microcestareceta;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cesta-receta")
public class Control {

    @Autowired
    private Servicio servicio;

    @PostMapping("/{operacionId}/agregar")
    public MedicamentoCesta agregar(
            @PathVariable String operacionId,
            @RequestBody MedicamentoCesta item) {
        return servicio.agregar(operacionId, item);
    }

    @DeleteMapping("/{operacionId}/quitar/{idMedicamento}")
    public Map<String, Object> quitar(
            @PathVariable String operacionId,
            @PathVariable Long idMedicamento) {
        boolean eliminado = servicio.quitar(operacionId, idMedicamento);
        return Map.of(
                "eliminado", eliminado,
                "mensaje", eliminado ? "Medicamento retirado" : "Medicamento no encontrado");
    }

    @GetMapping("/{operacionId}/listar")
    public List<MedicamentoCesta> listar(@PathVariable String operacionId) {
        return servicio.listar(operacionId);
    }

    @DeleteMapping("/{operacionId}/limpiar")
    public Map<String, String> limpiar(@PathVariable String operacionId) {
        servicio.limpiar(operacionId);
        return Map.of("mensaje", "Cesta de receta vaciada");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarError(IllegalArgumentException ex) {
        return Map.of("error", ex.getMessage());
    }
}
