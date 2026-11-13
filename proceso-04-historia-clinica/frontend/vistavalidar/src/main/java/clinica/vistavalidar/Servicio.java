package clinica.vistavalidar;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    private final String URL = "http://localhost:8081/validar";
    private final RestTemplate restTemplate = new RestTemplate();

    public Enfermera validar(Enfermera enfermera) {
        try {
            return restTemplate.postForObject(URL, enfermera, Enfermera.class);
        } catch (Exception e) {
            return null;
        }
    }
}