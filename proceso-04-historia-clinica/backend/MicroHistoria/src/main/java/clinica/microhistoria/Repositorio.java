package clinica.microhistoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface Repositorio extends JpaRepository<Historia, Long> {
    Optional<Historia> findByIdPaciente(Long idPaciente);
}
