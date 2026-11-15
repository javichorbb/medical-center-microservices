package pe.edu.utp.micromedicamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends JpaRepository<Medicamento, Long> {
}
