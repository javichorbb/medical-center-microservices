package ProgramacionHorario.VistaGestion;

import java.time.LocalTime;

public class FranjaHoraria {
    private Long idFranja;
    private Long idProgramacion;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public FranjaHoraria() {
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
}