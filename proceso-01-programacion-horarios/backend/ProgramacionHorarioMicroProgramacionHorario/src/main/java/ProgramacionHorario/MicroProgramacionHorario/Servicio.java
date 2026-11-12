package ProgramacionHorario.MicroProgramacionHorario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    public ProgramacionHorario registrarProgramacion(ProgramacionHorario p) {
        return repositorio.save(p);
    }

    public ProgramacionHorario buscarProgramacionPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public List<ProgramacionHorario> listarProgramaciones() {
        return repositorio.findAll();
    }

    public List<ProgramacionHorario> listarProgramacionesPorMedico(Long idMedico) {
        return repositorio.findByIdMedico(idMedico);
    }

    public void eliminarProgramacion(Long id) {
        repositorio.deleteById(id);
    }

    public ProgramacionHorario actualizarProgramacion(Long id, ProgramacionHorario p) {
        return repositorio.findById(id).map(x -> {
            x.setIdMedico(p.getIdMedico());
            x.setFecha(p.getFecha());
            x.setConsultorio(p.getConsultorio());
            x.setEstado(p.getEstado());
            return repositorio.save(x);
        }).orElse(null);
    }
}
