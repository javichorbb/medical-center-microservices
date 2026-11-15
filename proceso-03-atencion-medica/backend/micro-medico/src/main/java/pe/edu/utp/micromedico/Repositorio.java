package pe.edu.utp.micromedico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends JpaRepository<Medico, Long> {
}
