package CitaMedica.MicroValidar;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

    @Autowired
    private Servicio servicio;

    // 1. Valida enviando { "dni": "..." } y retorna el paciente (o null si no existe)
    @PostMapping("/validar")
    public Paciente validar(@RequestBody EntradaPaciente entrada) {
        return servicio.validar(entrada);
    }

    // 2. Buscar directamente por DNI con GET
    @GetMapping("/buscarPorDni/{dni}")
    public Paciente buscarPorDni(@PathVariable String dni) {
        return servicio.buscarPorDni(dni);
    }

    // 3. Buscar paciente por ID primario
    @GetMapping("/buscarPorId/{id}")
    public Paciente buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id);
    }

    // 4. Listar todos los pacientes registrados
    @GetMapping("/listar")
    public List<Paciente> listar() {
        return servicio.listar();
    }
}