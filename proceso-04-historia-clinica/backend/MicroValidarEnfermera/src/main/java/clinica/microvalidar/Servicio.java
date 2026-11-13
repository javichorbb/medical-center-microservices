package clinica.microvalidar;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    // Validación de credenciales para login de enfermera
    public Enfermera validarEnfermera(EntradaEnfermera ent) {
        if (ent == null || ent.getUsername() == null || ent.getPassword() == null) {
            return null;
        }
        return repositorio.findByUsernameAndPassword(ent.getUsername().trim(), ent.getPassword().trim());
    }

    // Listar todas las enfermeras
    public List<Enfermera> listarEnfermeras() {
        return repositorio.findAll();
    }

    // Buscar enfermera por ID
    public Enfermera buscarEnfermeraPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    // Registrar nueva enfermera
    public Enfermera registrarEnfermera(Enfermera e) {
        return repositorio.save(e);
    }

    // Actualizar enfermera por ID
    public Enfermera actualizarEnfermera(Long id, Enfermera e) {
        if (!repositorio.existsById(id)) {
            return null;
        }
        e.setIdEnfermera(id);
        return repositorio.save(e);
    }

    // Eliminar enfermera por ID
    public void eliminarEnfermera(Long id) {
        repositorio.deleteById(id);
    }
}
