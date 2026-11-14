package clinica.microhistoriaclinicacompuesto;

public class EntradaHistoria {

    private Long idPaciente;

    public EntradaHistoria() {
    }

    public EntradaHistoria(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
}
