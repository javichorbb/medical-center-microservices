package ProgramacionHorario.MicroCompProgramacionHorario;

public class SalidaProgramacion {

    private Long idProgramacion;
    private String fecha;
    private String consultorio;
    private String estado;
    private Medico medico;

    public SalidaProgramacion() {
    }

    public SalidaProgramacion(Long idProgramacion, String fecha, String consultorio, String estado, Medico medico) {
        this.idProgramacion = idProgramacion;
        this.fecha = fecha;
        this.consultorio = consultorio;
        this.estado = estado;
        this.medico = medico;
    }

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}

