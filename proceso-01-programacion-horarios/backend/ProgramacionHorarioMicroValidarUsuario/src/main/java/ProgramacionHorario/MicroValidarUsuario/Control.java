package ProgramacionHorario.MicroValidarUsuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

    @Autowired
    private Servicio servicio;

    // Endpoint de Login (Validación de Credenciales)
    @PostMapping("/validar")
    public Usuario validarUsuario(@RequestBody EntradaUsuario ent) {
        return servicio.validarUsuario(ent);
    }

    // Endpoint para Listar Usuarios
    @GetMapping("/usuarios/listar")
    public List<Usuario> listarUsuarios() {
        return servicio.listarUsuarios();
    }

    // Endpoint para Buscar Usuario por ID
    @GetMapping("/usuarios/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable Long id) {
        return servicio.buscarUsuarioPorId(id);
    }

    // Endpoint para Registrar Usuario
    @PostMapping("/usuarios/grabar")
    public void registrarUsuario(@RequestBody Usuario u) {
        servicio.registrarUsuario(u);
    }

    // Endpoint para Actualizar Usuario por ID
    @PutMapping("/usuarios/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario u) {
        return servicio.actualizarUsuario(id, u);
    }

    // Endpoint para Eliminar Usuario por ID
    @DeleteMapping("/usuarios/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        servicio.eliminarUsuario(id);
    }
}
