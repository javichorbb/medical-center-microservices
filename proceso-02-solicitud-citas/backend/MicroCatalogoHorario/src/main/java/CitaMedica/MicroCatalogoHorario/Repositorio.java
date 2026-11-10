package CitaMedica.MicroCatalogoHorario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositorio extends JpaRepository<Horario, Long> {

    List<Horario> findByIdMedicoAndEstado(Long idMedico, String estado);

    List<Horario> findByIdMedico(Long idMedico);
}