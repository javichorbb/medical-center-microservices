package ProgramacionHorario.MicroFranjaHoraria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalTime;

@Entity
public class FranjaHoraria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFranja;

    private Long idProgramacion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado = "DISPONIBLE";

    public FranjaHoraria() {
    }

    public FranjaHoraria(Long idFranja, Long idProgramacion, LocalTime horaInicio, LocalTime horaFin, String estado) {
        this.idFranja = idFranja;
        this.idProgramacion = idProgramacion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = (estado != null && !estado.isEmpty()) ? estado : "DISPONIBLE";
    }

    public Long getIdFranja() {
        return idFranja;
    }

    public void setIdFranja(Long idFranja) {
        this.idFranja = idFranja;
    }

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
