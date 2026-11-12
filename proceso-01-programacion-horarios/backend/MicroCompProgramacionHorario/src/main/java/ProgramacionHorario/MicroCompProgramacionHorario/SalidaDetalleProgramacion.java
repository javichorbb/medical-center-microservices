package ProgramacionHorario.MicroCompProgramacionHorario;

import java.util.List;

public class SalidaDetalleProgramacion {

    private Long idProgramacion;
    private String fecha;
    private String consultorio;
    private String estado;
    private Medico medico;
    private List<FranjaHoraria> franjasHorarias;

    public SalidaDetalleProgramacion() {
    }

    public SalidaDetalleProgramacion(Long idProgramacion, String fecha, String consultorio, String estado, Medico medico, List<FranjaHoraria> franjasHorarias) {
        this.idProgramacion = idProgramacion;
        this.fecha = fecha;
        this.consultorio = consultorio;
        this.estado = estado;
        this.medico = medico;
        this.franjasHorarias = franjasHorarias;
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

    public List<FranjaHoraria> getFranjasHorarias() {
        return franjasHorarias;
    }

    public void setFranjasHorarias(List<FranjaHoraria> franjasHorarias) {
        this.franjasHorarias = franjasHorarias;
    }
}
