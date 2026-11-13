package pe.edu.utp.microatencioncompuesto.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.microatencioncompuesto.dto.EntradaAtencionDTO;
import pe.edu.utp.microatencioncompuesto.servicio.AtencionCompuestoService;

@RestController
@RequestMapping("/atencion-compuesto")
public class AtencionCompuestoController {

    @Autowired
    private AtencionCompuestoService servicio;

    // 1. REGISTRAR ATENCIÓN MÉDICA CON CESTAS (Receta y Análisis)
    @PostMapping("/registrar/{idAtencion}")
    public Map<String, Object> registrar(
            @PathVariable String idAtencion,
            @RequestBody EntradaAtencionDTO entrada) {
        return servicio.registrarAtencion(idAtencion, entrada);
    }

    // 2. BUSCAR ATENCIÓN MÉDICA ENRIQUECIDA POR SU ID
    @GetMapping("/buscar/{idAtencion}")
    public Map<String, Object> buscar(@PathVariable Long idAtencion) {
        return servicio.buscarCompleta(idAtencion);
    }

    // 3. BUSCAR TODAS LAS ATENCIONES POR HISTORIA CLÍNICA (Para Proceso 4)
    @GetMapping("/historia/{idHistoria}")
    public List<Map<String, Object>> buscarPorHistoria(@PathVariable Long idHistoria) {
        return servicio.buscarPorHistoria(idHistoria);
    }

    // 4. LISTAR TODAS LAS ATENCIONES REGISTRADAS
    @GetMapping("/listar")
    public List<Map<String, Object>> listar() {
        return servicio.listarCompletas();
    }
}
