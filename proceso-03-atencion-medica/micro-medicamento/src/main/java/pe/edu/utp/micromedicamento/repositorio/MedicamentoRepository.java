
package pe.edu.utp.micromedicamento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.utp.micromedicamento.entidad.Medicamento;

@Repository
public interface MedicamentoRepository
        extends JpaRepository<Medicamento, Long> {

}
