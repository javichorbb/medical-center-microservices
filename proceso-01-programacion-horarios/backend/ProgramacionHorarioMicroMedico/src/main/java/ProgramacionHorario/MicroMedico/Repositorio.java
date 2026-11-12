package ProgramacionHorario.MicroMedico;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Repositorio extends JpaRepository<Medico, Long> {

    List<Medico> findByEspecialidad(String especialidad);

    @Query("SELECT DISTINCT m.especialidad FROM Medico m")
    List<String> listarEspecialidades();
}
