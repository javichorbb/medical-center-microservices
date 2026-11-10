package CitaMedica.MicroValidar;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    public Paciente validar(EntradaPaciente entrada) {
        if (entrada == null || entrada.getDni() == null) {
            return null;
        }
        return repositorio.findByDni(entrada.getDni().trim()).orElse(null);
    }

    public Paciente buscarPorDni(String dni) {
        if (dni == null) {
            return null;
        }
        return repositorio.findByDni(dni.trim()).orElse(null);
    }

    public Paciente buscarPorId(Long id) {
        if (id == null) {
            return null;
        }
        return repositorio.findById(id).orElse(null);
    }

    public List<Paciente> listar() {
        return repositorio.findAll();
    }
}