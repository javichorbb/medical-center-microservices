package ProgramacionHorario.MicroFranjaHoraria;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositorio extends JpaRepository<FranjaHoraria, Long> {

    List<FranjaHoraria> findByIdProgramacion(Long idProgramacion);
}

