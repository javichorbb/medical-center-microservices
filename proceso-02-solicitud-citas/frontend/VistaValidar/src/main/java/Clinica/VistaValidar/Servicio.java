
package Clinica.VistaValidar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    // MicroValidar corre en el puerto 8082
    private final String URL_BASE = "http://localhost:8082/pacientes";

    // Consume: POST http://localhost:8082/pacientes/validar
    public Paciente validar(EntradaPaciente entrada) {
        if (entrada == null || entrada.getDni() == null || entrada.getDni().trim().isEmpty()) {
            return null;
        }
        try {
            Paciente p = rt.postForObject(
                    URL_BASE + "/validar",
                    entrada,
                    Paciente.class);
            return p;
        } catch (Exception e) {
            System.err.println("Error al validar paciente: " + e.getMessage());
            return null;
        }
    }
}