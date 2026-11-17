package pe.edu.utp.microatencioncompuesto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atencion-compuesto")
public class Control {

    @Autowired
    private Servicio servicio;

    // 1. CESTA DE RECETA MÉDICA
    @PostMapping("/cesta-receta/agregar")
    public List<MedicamentoCesta> agregarMedicamento(@RequestBody Entrada ent) {
        return servicio.agregarMedicamento(ent);
    }

    @GetMapping("/cesta-receta")
    public List<MedicamentoCesta> verCestaReceta() {
        return servicio.verCestaReceta();
    }

    @DeleteMapping("/cesta-receta/quitar/{idMedicamento}")
    public List<MedicamentoCesta> quitarMedicamento(@PathVariable Long idMedicamento) {
        return servicio.quitarMedicamento(idMedicamento);
    }

    // 2. CESTA DE ANÁLISIS CLÍNICOS
    @PostMapping("/cesta-analisis/agregar")
    public List<AnalisisCesta> agregarAnalisis(@RequestBody Entrada ent) {
        return servicio.agregarAnalisis(ent);
    }

    @GetMapping("/cesta-analisis")
    public List<AnalisisCesta> verCestaAnalisis() {
        return servicio.verCestaAnalisis();
    }

    @DeleteMapping("/cesta-analisis/quitar/{idAnalisis}")
    public List<AnalisisCesta> quitarAnalisis(@PathVariable Long idAnalisis) {
        return servicio.quitarAnalisis(idAnalisis);
    }

    // 3. ATENCIÓN MÉDICA (Grabar, Buscar, Historia, Listar)
    @PostMapping("/grabar")
    public Salida grabarAtencion(@RequestBody Entrada ent) {
        return servicio.grabarAtencion(ent);
    }

    @GetMapping("/buscar/{id}")
    public Salida verSalida(@PathVariable Long id) {
        return servicio.verSalida(id);
    }

    @GetMapping("/historia/{idHistoria}")
    public List<Salida> buscarPorHistoria(@PathVariable Long idHistoria) {
        return servicio.buscarPorHistoria(idHistoria);
    }

    @GetMapping("/listar")
    public List<Salida> listarTodas() {
        return servicio.listarTodas();
    }
}
