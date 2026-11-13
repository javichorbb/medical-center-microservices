package pe.edu.utp.microatencion.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.microatencion.entidad.DetalleAnalisis;
import pe.edu.utp.microatencion.clave.DetalleAnalisisClave;

@Repository
public interface DetalleAnalisisRepository
        extends JpaRepository<DetalleAnalisis, DetalleAnalisisClave> {

    List<DetalleAnalisis> findByIdAtencion(Long idAtencion);
}
