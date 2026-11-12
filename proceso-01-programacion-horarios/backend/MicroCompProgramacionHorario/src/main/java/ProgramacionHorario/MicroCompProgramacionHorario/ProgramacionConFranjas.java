package ProgramacionHorario.MicroCompProgramacionHorario;

import java.util.List;

public class ProgramacionConFranjas {

    private Long idProgramacion;
    private String fecha;
    private String consultorio;
    private String estado;
    private List<FranjaHoraria> franjasHorarias;

    public ProgramacionConFranjas() {
    }

    public ProgramacionConFranjas(Long idProgramacion, String fecha, String consultorio, String estado, List<FranjaHoraria> franjasHorarias) {
        this.idProgramacion = idProgramacion;
        this.fecha = fecha;
        this.consultorio = consultorio;
        this.estado = estado;
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

    public List<FranjaHoraria> getFranjasHorarias() {
        return franjasHorarias;
    }

    public void setFranjasHorarias(List<FranjaHoraria> franjasHorarias) {
        this.franjasHorarias = franjasHorarias;
    }
}
