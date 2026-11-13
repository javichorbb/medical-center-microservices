package pe.edu.utp.microatencion.repositorio;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.microatencion.entidad.AtencionMedica;

@Repository
public interface AtencionMedicaRepository
        extends JpaRepository<AtencionMedica, Long> {

    List<AtencionMedica> findByIdHistoria(Long idHistoria);

    Optional<AtencionMedica> findByIdCita(Long idCita);
}
