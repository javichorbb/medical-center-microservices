package pe.edu.utp.microanalisis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends JpaRepository<AnalisisClinico, Long> {
}
