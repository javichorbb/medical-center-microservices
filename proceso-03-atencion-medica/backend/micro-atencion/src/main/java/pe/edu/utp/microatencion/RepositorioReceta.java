package pe.edu.utp.microatencion;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioReceta extends JpaRepository<DetalleReceta, DetalleRecetaClave> {

    List<DetalleReceta> findByIdAtencion(Long idAtencion);
}
