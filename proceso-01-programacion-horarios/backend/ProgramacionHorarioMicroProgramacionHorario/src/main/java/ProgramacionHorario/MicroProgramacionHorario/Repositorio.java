package ProgramacionHorario.MicroProgramacionHorario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositorio extends JpaRepository<ProgramacionHorario, Long> {

    List<ProgramacionHorario> findByIdMedico(Long idMedico);

    List<ProgramacionHorario> findByFecha(String fecha);
}

