package clinica.vistahistoria;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class Servicio {

    private final String URL = "http://localhost:8082/historia";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Historia> listar() {
        Historia[] historias = restTemplate.getForObject(URL + "/listar", Historia[].class);
        return historias != null ? Arrays.asList(historias) : List.of();
    }

    public List<Historia> buscarPorPaciente(String idPaciente) {
        Historia[] historias = restTemplate.getForObject(URL + "/paciente/" + idPaciente, Historia[].class);
        return historias != null ? Arrays.asList(historias) : List.of();
    }

    public Historia registrar(Historia historia) {
        return restTemplate.postForObject(URL + "/registrar", historia, Historia.class);
    }
}