
package pe.edu.utp.micromedico.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.micromedico.entidad.Medico;

@Repository
public interface MedicoRepository
        extends JpaRepository<Medico, Long> {

}
