package ProgramacionHorario.MicroProgramacionHorario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProgramacionHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgramacion;
    private Long idMedico;
    private String fecha;
    private String consultorio;
    private String estado;

    public ProgramacionHorario() {
    }

    public ProgramacionHorario(Long idProgramacion, Long idMedico, String fecha, String consultorio, String estado) {
        this.idProgramacion = idProgramacion;
        this.idMedico = idMedico;
        this.fecha = fecha;
        this.consultorio = consultorio;
        this.estado = estado;
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
}
