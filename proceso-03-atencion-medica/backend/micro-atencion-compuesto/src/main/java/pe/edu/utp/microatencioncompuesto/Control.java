package pe.edu.utp.microatencioncompuesto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atencion-compuesto")
public class Control {

    @Autowired
    private Servicio servicio;

    // 1. CATÁLOGOS (Médicos, Medicamentos y Tipos de Análisis)
    @GetMapping("/medicos")
    public List<Medico> listarMedicos() {
        return servicio.listarMedicos();
    }

    @GetMapping("/medicamentos")
    public List<Medicamento> listarMedicamentos() {
        return servicio.listarMedicamentos();
    }

    @GetMapping("/analisis")
    public List<AnalisisClinico> listarAnalisis() {
        return servicio.listarAnalisis();
    }

    // 2. CESTA DE RECETA MÉDICA EN MEMORIA (Devuelve ítems enriquecidos)
    @PostMapping("/cesta-receta/{idAtencion}/agregar")
    public List<SalidaRecetaItem> agregarMedicamento(
            @PathVariable String idAtencion,
            @RequestBody EntradaReceta item) {
        return servicio.agregarMedicamentoCesta(idAtencion, item);
    }

    @GetMapping("/cesta-receta/{idAtencion}")
    public List<SalidaRecetaItem> listarCestaReceta(@PathVariable String idAtencion) {
        return servicio.listarCestaReceta(idAtencion);
    }

    @DeleteMapping("/cesta-receta/{idAtencion}/quitar/{idMedicamento}")
    public List<SalidaRecetaItem> quitarMedicamento(
            @PathVariable String idAtencion,
            @PathVariable Long idMedicamento) {
        return servicio.quitarMedicamentoCesta(idAtencion, idMedicamento);
    }

    // 3. CESTA DE ANÁLISIS CLÍNICOS EN MEMORIA (Devuelve ítems enriquecidos)
    @PostMapping("/cesta-analisis/{idAtencion}/agregar")
    public List<SalidaAnalisisItem> agregarAnalisis(
            @PathVariable String idAtencion,
            @RequestBody EntradaAnalisis item) {
        return servicio.agregarAnalisisCesta(idAtencion, item);
    }

    @GetMapping("/cesta-analisis/{idAtencion}")
    public List<SalidaAnalisisItem> listarCestaAnalisis(@PathVariable String idAtencion) {
        return servicio.listarCestaAnalisis(idAtencion);
    }

    @DeleteMapping("/cesta-analisis/{idAtencion}/quitar/{idAnalisis}")
    public List<SalidaAnalisisItem> quitarAnalisis(
            @PathVariable String idAtencion,
            @PathVariable Long idAnalisis) {
        return servicio.quitarAnalisisCesta(idAtencion, idAnalisis);
    }

    // 4. GRABAR ATENCIÓN MÉDICA COMPLETA CON CESTAS (Receta y Análisis)
    @PostMapping("/atenciones/grabar/{idAtencion}")
    public SalidaAtencion registrar(
            @PathVariable String idAtencion,
            @RequestBody EntradaAtencion entrada) {
        return servicio.registrarAtencion(idAtencion, entrada);
    }

    // 5. BUSCAR ATENCIÓN MÉDICA ENRIQUECIDA POR SU ID
    @GetMapping("/atenciones/{idAtencion}")
    public SalidaAtencion buscar(@PathVariable Long idAtencion) {
        return servicio.buscarCompleta(idAtencion);
    }

    // 6. BUSCAR TODAS LAS ATENCIONES POR HISTORIA CLÍNICA (Para Proceso 4)
    @GetMapping("/atenciones/historia/{idHistoria}")
    public List<SalidaAtencion> buscarPorHistoria(@PathVariable Long idHistoria) {
        return servicio.buscarPorHistoria(idHistoria);
    }

    // 7. LISTAR TODAS LAS ATENCIONES REGISTRADAS
    @GetMapping("/atenciones")
    public List<SalidaAtencion> listar() {
        return servicio.listarCompletas();
    }
}
