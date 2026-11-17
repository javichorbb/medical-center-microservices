package clinica.microhistoriaclinicacompuesto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historia-compuesto")
public class Control {

    @Autowired
    private Servicio servicio;

    // 1. Grabar / Aperturar Historia Clínica (recibe Entrada y devuelve Salida consolidada)
    @PostMapping("/grabar")
    public Salida grabarHistoria(@RequestBody Entrada ent) {
        return servicio.grabarHistoria(ent);
    }

    // 2. Consultar Historia Clínica Completa por ID de Historia
    @GetMapping("/buscar/{id}")
    public Salida verSalida(@PathVariable Long id) {
        return servicio.verSalida(id);
    }

    // 3. Consultar Historia Clínica Completa por ID de Paciente
    @GetMapping("/paciente/{idPaciente}")
    public Salida buscarPorPaciente(@PathVariable Long idPaciente) {
        return servicio.buscarPorPaciente(idPaciente);
    }

    // 4. Listar Todas las Historias Clínicas del Sistema
    @GetMapping("/listar")
    public List<Salida> listarTodas() {
        return servicio.listarTodas();
    }
}
