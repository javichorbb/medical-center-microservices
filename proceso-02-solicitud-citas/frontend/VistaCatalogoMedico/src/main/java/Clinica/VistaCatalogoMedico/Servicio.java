package Clinica.VistaCatalogoMedico;

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

    // MicroCatalogoMedico corre en el puerto 8080
    private final String URL_BASE = "http://localhost:8080/medicos";

    // Consume: GET http://localhost:8080/medicos/especialidad/{esp}
    public List<Medico> listarPorEspecialidad(String esp) {
        if (esp == null || esp.trim().isEmpty()) {
            return Collections.emptyList();
        }
        try {
            Medico[] medicos = rt.getForObject(URL_BASE + "/especialidad/" + esp, Medico[].class);
            if (medicos != null) {
                return Arrays.asList(medicos);
            }
        } catch (Exception e) {
            System.err.println("Error al consultar médicos por especialidad: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    // Consume: GET http://localhost:8080/medicos/especialidades
    public List<String> listarEspecialidades() {
        try {
            String[] lista = rt.getForObject(URL_BASE + "/especialidades", String[].class);
            if (lista != null) {
                return Arrays.asList(lista);
            }
        } catch (Exception e) {
            System.err.println("Error al consultar lista de especialidades: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
