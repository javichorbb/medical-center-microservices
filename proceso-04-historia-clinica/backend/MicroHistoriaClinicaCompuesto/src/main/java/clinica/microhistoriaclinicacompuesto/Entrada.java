package clinica.microhistoriaclinicacompuesto;

public class Entrada {

    private Long idEnfermera;
    private Long idPaciente;

    public Entrada() {
    }

    public Entrada(Long idEnfermera, Long idPaciente) {
        this.idEnfermera = idEnfermera;
        this.idPaciente = idPaciente;
    }

    public Long getIdEnfermera() {
        return idEnfermera;
    }

    public void setIdEnfermera(Long idEnfermera) {
        this.idEnfermera = idEnfermera;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
}
