package ProgramacionHorario.MicroCompProgramacionHorario;

import java.time.LocalDate;

public class EntradaProgramacion {

    private Long idMedico;
    private LocalDate fecha;
    private String consultorio;
    private String estado;

    public EntradaProgramacion() {
    }

    public EntradaProgramacion(Long idMedico, LocalDate fecha, String consultorio, String estado) {
        this.idMedico = idMedico;
        this.fecha = fecha;
        this.consultorio = consultorio;
        this.estado = estado;
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
}
