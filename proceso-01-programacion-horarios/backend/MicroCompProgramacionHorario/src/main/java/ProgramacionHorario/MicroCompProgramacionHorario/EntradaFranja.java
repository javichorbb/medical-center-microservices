package ProgramacionHorario.MicroCompProgramacionHorario;

public class EntradaFranja {

    private Long idProgramacion;
    private String horaInicio;
    private String horaFin;

    public EntradaFranja() {
    }

    public EntradaFranja(Long idProgramacion, String horaInicio, String horaFin) {
        this.idProgramacion = idProgramacion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
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
}

