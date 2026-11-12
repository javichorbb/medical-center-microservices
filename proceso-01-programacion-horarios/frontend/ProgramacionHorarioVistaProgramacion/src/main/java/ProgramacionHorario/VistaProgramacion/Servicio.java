package ProgramacionHorario.VistaProgramacion;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    public void grabar(Medico m) {
        rt.postForObject("http://localhost:8082/medico/grabar", m, void.class);
    }

    public Medico buscar(Long id) {
        return rt.getForObject("http://localhost:8082/medico/buscar/" + id, Medico.class);
    }

    public List<Medico> buscarPorEspecialidad(String especialidad) {
        return rt.getForObject("http://localhost:8082/medico/especialidad/"+especialidad, List.class);
    }

    public List<Medico> listar() {
        return rt.getForObject("http://localhost:8082/medico/listar", List.class);
    }

    public void eliminar(Long id) {
        rt.delete("http://localhost:8082/medico/eliminar/" + id);
    }

    public void actualizar(Long id, Medico med) {
        rt.put("http://localhost:8082/medico/actualizar/" + id, med);
    }
}
