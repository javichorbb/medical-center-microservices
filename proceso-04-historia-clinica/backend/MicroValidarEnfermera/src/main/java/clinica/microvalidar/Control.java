package clinica.microvalidar;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

    @Autowired
    private Servicio servicio;

    // Endpoint de Login (Validación de Credenciales de Enfermera)
    @PostMapping("/validar")
    public Enfermera validarEnfermera(@RequestBody EntradaEnfermera ent) {
        return servicio.validarEnfermera(ent);
    }

    // Endpoint para Listar Todas las Enfermeras
    @GetMapping("/enfermeras/listar")
    public List<Enfermera> listarEnfermeras() {
        return servicio.listarEnfermeras();
    }

    // Endpoint para Buscar Enfermera por ID
    @GetMapping("/enfermeras/{id}")
    public Enfermera buscarEnfermeraPorId(@PathVariable Long id) {
        return servicio.buscarEnfermeraPorId(id);
    }

    // Endpoint para Registrar Nueva Enfermera
    @PostMapping("/enfermeras/grabar")
    public Enfermera registrarEnfermera(@RequestBody Enfermera e) {
        return servicio.registrarEnfermera(e);
    }

    // Endpoint para Actualizar Datos de Enfermera por ID
    @PutMapping("/enfermeras/{id}")
    public Enfermera actualizarEnfermera(@PathVariable Long id, @RequestBody Enfermera e) {
        return servicio.actualizarEnfermera(id, e);
    }

    // Endpoint para Eliminar Enfermera por ID
    @DeleteMapping("/enfermeras/{id}")
    public void eliminarEnfermera(@PathVariable Long id) {
        servicio.eliminarEnfermera(id);
    }
}
