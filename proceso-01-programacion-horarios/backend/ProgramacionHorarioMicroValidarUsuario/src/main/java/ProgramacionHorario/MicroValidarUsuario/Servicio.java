package ProgramacionHorario.MicroValidarUsuario;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    public Usuario validarUsuario(EntradaUsuario ent) {
        if (ent == null || ent.getUsername() == null || ent.getPassword() == null) {
            return null;
        }
        Optional<Usuario> userOpt = repositorio.findByUsernameAndPassword(ent.getUsername(), ent.getPassword());
        return userOpt.orElse(null);
    }

    public void registrarUsuario(Usuario u) {
        repositorio.save(u);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return repositorio.findAll();
    }

    public void eliminarUsuario(Long id) {
        repositorio.deleteById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario u) {
        return repositorio.findById(id).map(x -> {
            x.setNombre(u.getNombre());
            x.setUsername(u.getUsername());
            x.setPassword(u.getPassword());
            x.setRol(u.getRol());
            return repositorio.save(x);
        }).orElse(null);
    }
}
