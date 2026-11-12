package ProgramacionHorario.VistaRegistro;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    public void grabar(ProgramacionHorario p) {
        rt.postForObject("http://localhost:8081/programacion/grabar", p, Void.class);
    }

    public ProgramacionHorario buscar(Long id) {
        return rt.getForObject("http://localhost:8081/programacion/buscar/" + id, ProgramacionHorario.class);
    }

    public List listar() {
        return rt.getForObject("http://localhost:8081/programacion/listar", List.class);
    }

    public void eliminar(Long id) {
        rt.delete("http://localhost:8081/programacion/eliminar/" + id);
    }

    public void actualizar(Long id, ProgramacionHorario p) {
        rt.put("http://localhost:8081/programacion/actualizar/" + id, p);
    }
}
