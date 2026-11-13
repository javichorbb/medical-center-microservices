package clinica.microhistoria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Historia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoria;
    private Long idPaciente;
    private LocalDate fechaApertura;

    public Historia() {
    }

    public Historia(Long idHistoria, Long idPaciente, LocalDate fechaApertura) {
        this.idHistoria = idHistoria;
        this.idPaciente = idPaciente;
        this.fechaApertura = fechaApertura;
    }

    public Long getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Long idHistoria) {
        this.idHistoria = idHistoria;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
}
