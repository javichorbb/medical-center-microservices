package clinica.microhistoriaclinicacompuesto;

import java.time.LocalDate;
import java.util.List;

public class SalidaHistoria {

    private Long idHistoria;
    private LocalDate fechaApertura;
    private SalidaPaciente paciente;
    private List<AtencionMedica> atencionesMedicas;

    public SalidaHistoria() {
    }

    public SalidaHistoria(Long idHistoria, LocalDate fechaApertura, SalidaPaciente paciente, List<AtencionMedica> atencionesMedicas) {
        this.idHistoria = idHistoria;
        this.fechaApertura = fechaApertura;
        this.paciente = paciente;
        this.atencionesMedicas = atencionesMedicas;
    }

    public Long getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Long idHistoria) {
        this.idHistoria = idHistoria;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public SalidaPaciente getPaciente() {
        return paciente;
    }

    public void setPaciente(SalidaPaciente paciente) {
        this.paciente = paciente;
    }

    public List<AtencionMedica> getAtencionesMedicas() {
        return atencionesMedicas;
    }

    public void setAtencionesMedicas(List<AtencionMedica> atencionesMedicas) {
        this.atencionesMedicas = atencionesMedicas;
    }
}
