package ProgramacionHorario.MicroValidarUsuario;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsernameAndPassword(String username, String password);
}
