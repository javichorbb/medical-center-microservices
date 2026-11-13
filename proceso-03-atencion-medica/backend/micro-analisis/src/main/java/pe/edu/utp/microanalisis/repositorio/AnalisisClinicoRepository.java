
package pe.edu.utp.microanalisis.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.microanalisis.entidad.AnalisisClinico;

@Repository
public interface AnalisisClinicoRepository
        extends JpaRepository<AnalisisClinico, Long> {

}
