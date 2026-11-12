package ProgramacionHorario.MicroCompProgramacionHorario;

public class SalidaFranja {

    private Long idFranja;
    private String horaInicio;
    private String horaFin;
    private String estado;
    private SalidaProgramacion programacionHorario;

    public SalidaFranja() {
    }

    public SalidaFranja(Long idFranja, String horaInicio, String horaFin, String estado, SalidaProgramacion programacionHorario) {
        this.idFranja = idFranja;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.programacionHorario = programacionHorario;
    }

    public Long getIdFranja() {
        return idFranja;
    }

    public void setIdFranja(Long idFranja) {
        this.idFranja = idFranja;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public SalidaProgramacion getProgramacionHorario() {
        return programacionHorario;
    }

    public void setProgramacionHorario(SalidaProgramacion programacionHorario) {
        this.programacionHorario = programacionHorario;
    }
}
