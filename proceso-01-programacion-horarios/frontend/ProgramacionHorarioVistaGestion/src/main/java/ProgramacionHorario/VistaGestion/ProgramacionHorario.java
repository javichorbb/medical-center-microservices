package ProgramacionHorario.VistaGestion;

public class ProgramacionHorario {

    private Long idProgramacion;
    private Long idMed;
    private String fec;
    private String estado;

    public ProgramacionHorario() {
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

    public Long getIdMed() {
        return idMed;
    }

    public void setIdMed(Long idMed) {
        this.idMed = idMed;
    }
}
