package pe.edu.utp.microatencion;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends JpaRepository<AtencionMedica, Long> {

    List<AtencionMedica> findByIdHistoria(Long idHistoria);

    Optional<AtencionMedica> findByIdCita(Long idCita);
}
