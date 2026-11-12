package ProgramacionHorario.MicroFranjaHoraria;

import java.time.Duration;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    public FranjaHoraria registrarFranja(FranjaHoraria f) {
        validarDuracion(f);
        if (f.getEstado() == null || f.getEstado().trim().isEmpty()) {
            f.setEstado("DISPONIBLE");
        }
        return repositorio.save(f);
    }

    public FranjaHoraria buscarFranjaPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public List<FranjaHoraria> listarFranjas() {
        return repositorio.findAll();
    }

    public List<FranjaHoraria> listarFranjasPorProgramacion(Long idProgramacion) {
        return repositorio.findByIdProgramacion(idProgramacion);
    }

    public void eliminarFranja(Long id) {
        repositorio.deleteById(id);
    }

    public FranjaHoraria actualizarFranja(Long id, FranjaHoraria f) {
        validarDuracion(f);
        return repositorio.findById(id).map(x -> {
            x.setIdProgramacion(f.getIdProgramacion());
            x.setHoraInicio(f.getHoraInicio());
            x.setHoraFin(f.getHoraFin());
            if (f.getEstado() != null && !f.getEstado().trim().isEmpty()) {
                x.setEstado(f.getEstado());
            }
            return repositorio.save(x);
        }).orElse(null);
    }

    private void validarDuracion(FranjaHoraria f) {
        if (f == null || f.getHoraInicio() == null || f.getHoraFin() == null) {
            throw new IllegalArgumentException("La hora de inicio y hora de fin son obligatorias.");
        }
        if (f.getHoraFin().isBefore(f.getHoraInicio()) || f.getHoraFin().equals(f.getHoraInicio())) {
            throw new IllegalArgumentException("La hora de fin debe ser posterior a la hora de inicio.");
        }
        long minutos = Duration.between(f.getHoraInicio(), f.getHoraFin()).toMinutes();
        if (minutos > 20) {
            throw new IllegalArgumentException("El intervalo de atención no puede exceder los 20 minutos como máximo (actual: " + minutos + " min).");
        }
    }
}
