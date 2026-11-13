package pe.edu.utp.microhistoria.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.microhistoria.entidad.HistoriaClinica;

@Repository
public interface HistoriaClinicaRepository
        extends JpaRepository<HistoriaClinica, Long> {

    Optional<HistoriaClinica> findByIdPaciente(Long idPaciente);
}
