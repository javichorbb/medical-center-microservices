package ProgramacionHorario.VistaGestion;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Service
public class Servicio {

    @Autowired
    private RestTemplate rt;

    public List<Medico> listarMedicos() {
        Medico[] m = rt.getForObject(
                "http://MICROGESTION/gestion/medicos",
                Medico[].class
        );
        return Arrays.asList(m);
    }

    public Medico buscarMedico(Long id) {
        return rt.getForObject(
                "http://MICROGESTION/gestion/medico/buscar/" + id,
                Medico.class
        );
    }

    public void grabarMedico(Medico m) {
        rt.postForObject(
                "http://MICROGESTION/gestion/medico/grabar",
                m,
                Void.class
        );
    }

    public void eliminarMedico(Long id) {
        rt.delete(
                "http://MICROGESTION/gestion/medico/eliminar/" + id
        );
    }

    public Medico actualizarMedico(Long id, Medico m) {
        rt.put(
                "http://MICROGESTION/gestion/medico/actualizar/" + id,
                m
        );
        return buscarMedico(id);
    }

    public List<ProgramacionHorario> listarProgramaciones() {
        ProgramacionHorario[] p = rt.getForObject(
                "http://MICROGESTION/gestion/programaciones",
                ProgramacionHorario[].class
        );
        return Arrays.asList(p);
    }

    public Usuario validar(Entrada ent) {
        return rt.postForObject(
                "http://MICROGESTION/gestion/validar",
                ent,
                Usuario.class
        );
    }

    public List<Usuario> listarUsuarios() {
        Usuario[] u = rt.getForObject(
                "http://MICROGESTION/gestion/usuarios",
                Usuario[].class
        );
        return Arrays.asList(u);
    }

    public Usuario buscarUsuario(Long id) {
        return rt.getForObject(
                "http://MICROGESTION/gestion/usuario/buscar/" + id,
                Usuario.class
        );
    }

    public void grabarUsuario(Usuario u) {
        rt.postForObject(
                "http://MICROGESTION/gestion/usuario/grabar",
                u,
                Void.class
        );
    }

    public void eliminarUsuario(Long id) {
        rt.delete(
                "http://MICROGESTION/gestion/usuario/eliminar/" + id
        );
    }

    public Usuario actualizarUsuario(Long id, Usuario u) {
        rt.put(
                "http://MICROGESTION/gestion/usuario/actualizar/" + id,
                u
        );
        return buscarUsuario(id);
    }

    public ProgramacionHorario grabarProgramacion(ProgramacionHorario p) {
        return rt.postForObject(
                "http://MICROGESTION/gestion/programacion/grabar",
                p,
                ProgramacionHorario.class
        );
    }

    public ProgramacionHorario buscarProgramacion(Long id) {
        return rt.getForObject(
                "http://MICROGESTION/gestion/programacion/buscar/" + id,
                ProgramacionHorario.class
        );
    }

    public void eliminarProgramacion(Long id) {
        rt.delete(
                "http://MICROGESTION/gestion/programacion/eliminar/" + id
        );
    }

    public ProgramacionHorario actualizarProgramacion(Long id, ProgramacionHorario p) {
        rt.put(
                "http://MICROGESTION/gestion/programacion/actualizar/" + id,
                p
        );
        return buscarProgramacion(id);
    }

    public List<FranjaHoraria> listarFranjas() {
        return Arrays.asList(
                rt.getForObject(
                        "http://MICROGESTION/gestion/franjas",
                        FranjaHoraria[].class
                )
        );
    }

    public FranjaHoraria grabarFranja(FranjaHoraria f) {
        return rt.postForObject(
                "http://MICROGESTION/gestion/franja/grabar",
                f,
                FranjaHoraria.class
        );
    }

    public FranjaHoraria buscarFranja(Long id) {
        return rt.getForObject(
                "http://MICROGESTION/gestion/franja/buscar/" + id,
                FranjaHoraria.class
        );
    }

    public void eliminarFranja(Long id) {
        rt.delete(
                "http://MICROGESTION/gestion/franja/eliminar/" + id
        );
    }

    public FranjaHoraria actualizarFranja(Long id, FranjaHoraria f) {
        rt.put(
                "http://MICROGESTION/gestion/franja/actualizar/" + id,
                f
        );
        return buscarFranja(id);
    }
}
