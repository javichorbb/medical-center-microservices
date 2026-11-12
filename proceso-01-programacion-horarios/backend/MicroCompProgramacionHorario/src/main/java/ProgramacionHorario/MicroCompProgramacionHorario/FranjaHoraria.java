package ProgramacionHorario.MicroCompProgramacionHorario;

public class FranjaHoraria {

    private Long idFranja;
    private Long idProgramacion;
    private String horaInicio;
    private String horaFin;
    private String estado;

    public FranjaHoraria() {
    }

    public FranjaHoraria(Long idFranja, Long idProgramacion, String horaInicio, String horaFin, String estado) {
        this.idFranja = idFranja;
        this.idProgramacion = idProgramacion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
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
}

