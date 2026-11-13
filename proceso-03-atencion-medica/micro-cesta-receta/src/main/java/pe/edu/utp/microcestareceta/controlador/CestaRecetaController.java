package pe.edu.utp.microcestareceta.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.microcestareceta.dto.MedicamentoCestaDTO;
import pe.edu.utp.microcestareceta.servicio.CestaRecetaService;

@RestController
@RequestMapping("/cesta-receta")
public class CestaRecetaController {

    @Autowired
    private CestaRecetaService servicio;

    // Agregar o actualizar medicamento
    @PostMapping("/{operacionId}/agregar")
    public MedicamentoCestaDTO agregar(
            @PathVariable String operacionId,
            @RequestBody MedicamentoCestaDTO item) {

        return servicio.agregar(operacionId, item);
    }

    // Quitar medicamento de la cesta
    @DeleteMapping("/{operacionId}/quitar/{idMedicamento}")
    public Map<String, Object> quitar(
            @PathVariable String operacionId,
            @PathVariable Long idMedicamento) {

        boolean eliminado =
            servicio.quitar(operacionId, idMedicamento);

        return Map.of(
            "eliminado", eliminado,
            "mensaje", eliminado
                ? "Medicamento retirado"
                : "Medicamento no encontrado");
    }

    // Listar medicamentos de una cesta
    @GetMapping("/{operacionId}/listar")
    public List<MedicamentoCestaDTO> listar(
            @PathVariable String operacionId) {

        return servicio.listar(operacionId);
    }

    // Vaciar cesta
    @DeleteMapping("/{operacionId}/limpiar")
    public Map<String, String> limpiar(
            @PathVariable String operacionId) {

        servicio.limpiar(operacionId);

        return Map.of(
            "mensaje", "Cesta de receta vaciada");
    }

    // Devolver HTTP 400 ante datos invalidos
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarError(
            IllegalArgumentException ex) {

        return Map.of("error", ex.getMessage());
    }
}
