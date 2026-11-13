package clinica.micropaciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends JpaRepository<Paciente, Long> {
    Paciente findByDni(String dni);
}
