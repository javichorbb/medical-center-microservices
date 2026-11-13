package clinica.microvalidar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends JpaRepository<Enfermera, Long> {
    Enfermera findByUsernameAndPassword(String username, String password);
}
