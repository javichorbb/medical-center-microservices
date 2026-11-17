package clinica.microhistoriaclinicacompuesto;

import java.time.LocalDate;

public class Historia {

    private Long idHistoria;
    private Long idEnfermera;
    private Long idPaciente;
    private LocalDate fechaApertura;

    public Historia() {
    }

    public Historia(Long idHistoria, Long idEnfermera, Long idPaciente, LocalDate fechaApertura) {
        this.idHistoria = idHistoria;
        this.idEnfermera = idEnfermera;
        this.idPaciente = idPaciente;
        this.fechaApertura = fechaApertura;
    }

    public Long getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Long idHistoria) {
        this.idHistoria = idHistoria;
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

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
}
