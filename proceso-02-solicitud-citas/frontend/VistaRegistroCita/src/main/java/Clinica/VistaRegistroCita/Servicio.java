
package Clinica.VistaRegistroCita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    // MicroRegistroCita corre en el puerto 8083
    private final String URL_BASE = "http://localhost:8083/citas";

    // Consume: POST http://localhost:8083/citas/registrar
    public CitaMedica grabar(CitaMedica cita) {
        try {
            CitaMedica nueva = rt.postForObject(
                    URL_BASE + "/registrar",
                    cita,
                    CitaMedica.class);
            return nueva;
        } catch (Exception e) {
            System.err.println("Error al registrar la cita: " + e.getMessage());
            return null;
        }
    }
}