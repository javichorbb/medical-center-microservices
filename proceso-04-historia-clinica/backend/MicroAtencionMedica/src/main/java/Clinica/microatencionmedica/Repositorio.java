package Clinica.microatencionmedica;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Repositorio extends JpaRepository<AtencionMedica, Long> {
    List<AtencionMedica> findByIdHistoria(Long idHistoria);
}
