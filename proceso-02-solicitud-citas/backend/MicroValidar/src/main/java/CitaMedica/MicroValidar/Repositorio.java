package CitaMedica.MicroValidar;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface Repositorio extends JpaRepository<Paciente ,Long> {
    Optional<Paciente> findByDni(String dni);
}
