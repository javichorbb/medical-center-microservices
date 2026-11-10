
package Clinica.VistaCatalogoHorario;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    // MicroCatalogoHorario corre en el puerto 8083
    private final String URL_BASE = "http://localhost:8081/horarios";

    // Consume: GET http://localhost:8081/horarios/disponibles/medico/{idMed}
    public List<Horario> listarDisponibles(Long idMed) {
        if (idMed == null) {
            return Collections.emptyList();
        }
        try {
            Horario[] horarios = rt.getForObject(
                    URL_BASE + "/disponibles/medico/" + idMed,
                    Horario[].class);
            if (horarios != null) {
                return Arrays.asList(horarios);
            }
        } catch (Exception e) {
            System.err.println("Error al consultar horarios: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
