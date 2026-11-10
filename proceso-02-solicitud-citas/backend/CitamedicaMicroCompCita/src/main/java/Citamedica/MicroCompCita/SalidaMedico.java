package Citamedica.MicroCompCita;

import java.util.List;

public class SalidaMedico {

    private Long idMedico;
    private String nombreMedico;
    private String especialidad;
    private List<Horario> horarios;

    public SalidaMedico() {
    }

    public SalidaMedico(Long idMedico, String nombreMedico, String especialidad, List<Horario> horarios) {
        this.idMedico = idMedico;
        this.nombreMedico = nombreMedico;
        this.especialidad = especialidad;
        this.horarios = horarios;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
