package Citamedica.MicroCompCita;

public class Entrada {

    private Long idHorario;
    private Long idPaciente;

    public Entrada() {
    }

    public Entrada(Long idHorario, Long idPaciente) {
        this.idHorario = idHorario;
        this.idPaciente = idPaciente;
    }

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
}
