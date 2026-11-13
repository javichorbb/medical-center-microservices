package clinica.vistapaciente;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class Servicio {

    private final String URL = "http://localhost:8080/paciente";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Paciente> listar() {
        Paciente[] pacientes = restTemplate.getForObject(URL + "/listar", Paciente[].class);
        return Arrays.asList(pacientes);
    }

    public void guardar(Paciente paciente) {
        restTemplate.postForEntity(URL + "/registrar", paciente, Paciente.class);
    }

    public Paciente buscarPorId(String id) {
        return restTemplate.getForObject(URL + "/buscar/" + id, Paciente.class);
    }

    // NUEVO: Método para eliminar en el backend
    public void eliminar(String id) {
        restTemplate.delete(URL + "/eliminar/" + id);
    }

    // NUEVO: Método para actualizar en el backend
    public void actualizar(Paciente paciente) {
        restTemplate.put(URL + "/actualizar", paciente);
    }
}