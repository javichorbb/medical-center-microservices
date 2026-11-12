package ProgramacionHorario.VistaValidar;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    public Usuario validar(Entrada ent) {
        return rt.postForObject("http://localhost:8084/validar", ent, Usuario.class);
    }

    public void grabar(Usuario u) {
        rt.postForObject("http://localhost:8084/validar/grabar", u, Void.class);
    }

    public Usuario buscar(Long id) {
        return rt.getForObject("http://localhost:8084/validar/buscar/" + id, Usuario.class);
    }

    public List<Usuario> listar() {
        return rt.getForObject("http://localhost:8084/validar/listar", List.class);
    }

    public void eliminar(Long id) {
        rt.delete("http://localhost:8084/validar/eliminar/" + id);
    }

    public void actualizar(Long id, Usuario u) {
        rt.put("http://localhost:8084/validar/actualizar/" + id, u);
    }
}
