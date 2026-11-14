package ProgramacionHorario.MicroCompProgramacionHorario;

import java.time.LocalDate;
import java.util.List;

public class SalidaProgramacion {

    private Long idProgramacion;
    private Long idMedico;
    private LocalDate fecha;
    private String consultorio;
    private String estado;
    private List<FranjaHoraria> franjasHorarias;

    public SalidaProgramacion() {
    }

    public SalidaProgramacion(Long idProgramacion, Long idMedico, LocalDate fecha, String consultorio, String estado, List<FranjaHoraria> franjasHorarias) {
        this.idProgramacion = idProgramacion;
        this.idMedico = idMedico;
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

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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
