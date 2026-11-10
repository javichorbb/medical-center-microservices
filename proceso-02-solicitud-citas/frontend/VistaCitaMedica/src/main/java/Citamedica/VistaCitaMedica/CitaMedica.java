package Citamedica.VistaCitaMedica;

public class CitaMedica {
    private Long idCita;
    private Long idHorario;
    private Long idPaciente;
    private String estado;

    public CitaMedica() {}
    public CitaMedica(Long idCita, Long idHorario, Long idPaciente, String estado) {
        this.idCita = idCita;
        this.idHorario = idHorario;
        this.idPaciente = idPaciente;
        this.estado = estado;
    }

    public Long getIdCita() { return idCita; }
    public void setIdCita(Long idCita) { this.idCita = idCita; }

    public Long getIdHorario() { return idHorario; }
    public void setIdHorario(Long idHorario) { this.idHorario = idHorario; }

    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}