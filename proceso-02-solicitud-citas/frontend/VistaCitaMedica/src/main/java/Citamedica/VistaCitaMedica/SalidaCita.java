package Citamedica.VistaCitaMedica;

public class SalidaCita {

    private Long idCita;
    private String estado;
    private Paciente paciente;
    private Medico medico;
    private Horario horario;

    public SalidaCita() {
    }

    public SalidaCita(Long idCita, String estado, Paciente paciente, Medico medico, Horario horario) {
        this.idCita = idCita;
        this.estado = estado;
        this.paciente = paciente;
        this.medico = medico;
        this.horario = horario;
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}