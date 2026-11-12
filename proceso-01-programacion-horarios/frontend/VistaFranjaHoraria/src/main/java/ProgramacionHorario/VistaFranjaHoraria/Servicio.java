package ProgramacionHorario.VistaFranjaHoraria;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    public void grabar(FranjaHoraria det) {
        rt.postForObject("http://localhost:8083/franja/grabar", det, Void.class);
    }

    public FranjaHoraria buscar(Long id) {
        return rt.getForObject("http://localhost:8083/franja/buscar/" + id, FranjaHoraria.class);
    }

    public List<FranjaHoraria> listar() {
        return rt.getForObject("http://localhost:8083/franja/listar", List.class);
    }

    public void eliminar(Long id) {
        rt.delete("http://localhost:8083/franja/eliminar/" + id);
    }

    public void actualizar(Long id, FranjaHoraria det) {
        rt.put("http://localhost:8083/franja/actualizar/" + id, det);
    }
}
