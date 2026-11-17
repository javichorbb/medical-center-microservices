package CitaMedica.MicroValidar;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    public Paciente grabar(Paciente p) {
        return repositorio.save(p);
    }

    public Paciente validar(EntradaPaciente ent) {
        if (ent == null || ent.getDni() == null) {
            return null;
        }
        return repositorio.findByDni(ent.getDni().trim()).orElse(null);
    }

    public Paciente buscarPorDni(String dni) {
        if (dni == null) {
            return null;
        }
        return repositorio.findByDni(dni.trim()).orElse(null);
    }

    public Paciente buscarPorId(Long idPaciente) {
        if (idPaciente == null) {
            return null;
        }
        return repositorio.findById(idPaciente).orElse(null);
    }

    public List<Paciente> listar() {
        return repositorio.findAll();
    }
}