package pe.edu.utp.microatencion.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.microatencion.entidad.DetalleReceta;
import pe.edu.utp.microatencion.clave.DetalleRecetaClave;

@Repository
public interface DetalleRecetaRepository
        extends JpaRepository<DetalleReceta, DetalleRecetaClave> {

    List<DetalleReceta> findByIdAtencion(Long idAtencion);
}
