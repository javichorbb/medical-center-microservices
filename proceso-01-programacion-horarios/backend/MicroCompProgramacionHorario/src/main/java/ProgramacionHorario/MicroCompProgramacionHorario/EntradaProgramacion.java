package ProgramacionHorario.MicroCompProgramacionHorario;

public class EntradaProgramacion {

    private Long idMedico;
    private String fecha;
    private String consultorio;

    public EntradaProgramacion() {
    }

    public EntradaProgramacion(Long idMedico, String fecha, String consultorio) {
        this.idMedico = idMedico;
        this.fecha = fecha;
        this.consultorio = consultorio;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
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
}

