package clinica.micropaciente;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    // Listar todos los pacientes
    public List<Paciente> listarPacientes() {
        return repositorio.findAll();
    }

    // Buscar paciente por ID
    public Paciente buscarPacientePorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    // Buscar paciente por DNI (fundamental para verificar primera cita médica)
    public Paciente buscarPacientePorDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return null;
        }
        return repositorio.findByDni(dni.trim());
    }

    // Registrar nuevo paciente
    public Paciente registrarPaciente(Paciente paciente) {
        return repositorio.save(paciente);
    }

    // Actualizar paciente por ID
    public Paciente actualizarPaciente(Long id, Paciente paciente) {
        if (!repositorio.existsById(id)) {
            return null;
        }
        paciente.setIdPaciente(id);
        return repositorio.save(paciente);
    }

    // Eliminar paciente por ID
    public void eliminarPaciente(Long id) {
        repositorio.deleteById(id);
    }
}
