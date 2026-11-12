package ProgramacionHorario.VistaRegistro;

public class ProgramacionHorario {

    private Long idProgramacion;
    private String fec;
    private String estado;

    public ProgramacionHorario() {
    }

    public ProgramacionHorario(Long idProgramacion, String fec, String estado) {
        this.idProgramacion = idProgramacion;
        this.fec = fec;
        this.estado = estado;
    }

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public String getFec() {
        return fec;
    }

    public void setFec(String fec) {
        this.fec = fec;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
