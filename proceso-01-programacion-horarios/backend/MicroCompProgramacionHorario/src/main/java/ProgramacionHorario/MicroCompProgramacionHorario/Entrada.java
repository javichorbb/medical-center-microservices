package ProgramacionHorario.MicroCompProgramacionHorario;

import java.time.LocalDate;

public class Entrada {

    private Long idUsuario;
    private Long idMedico;
    private LocalDate fecha;
    private String consultorio;

    public Entrada() {
    }

    public Entrada(Long idUsuario, Long idMedico, LocalDate fecha, String consultorio) {
        this.idUsuario = idUsuario;
        this.idMedico = idMedico;
        this.fecha = fecha;
        this.consultorio = consultorio;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
}
