package ProgramacionHorario.MicroMedico;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    public void registrarMedico(Medico m) {
        repositorio.save(m);
    }

    public Medico buscarMedicoPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public List<Medico> listarMedicos() {
        return repositorio.findAll();
    }

    public List<Medico> listarMedicosPorEspecialidad(String especialidad) {
        return repositorio.findByEspecialidad(especialidad);
    }

    public List<String> listarEspecialidades() {
        return repositorio.listarEspecialidades();
    }

    public void eliminarMedico(Long id) {
        repositorio.deleteById(id);
    }

    public Medico actualizarMedico(Long id, Medico m) {
        return repositorio.findById(id).map(x -> {
            x.setNombre(m.getNombre());
            x.setEspecialidad(m.getEspecialidad());
            return repositorio.save(x);
        }).orElse(null);
    }
}
