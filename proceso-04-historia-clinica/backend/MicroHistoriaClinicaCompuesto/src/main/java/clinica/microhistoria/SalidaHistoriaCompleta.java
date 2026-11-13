package clinica.microhistoria;

import java.time.LocalDate;
import java.util.List;

public class SalidaHistoriaCompleta {

    private Long idHistoria;
    private LocalDate fechaApertura;
    private SalidaPaciente paciente;
    private List<?> atencionesMedicas;

    public SalidaHistoriaCompleta() {
    }

    public SalidaHistoriaCompleta(Long idHistoria, LocalDate fechaApertura, SalidaPaciente paciente, List<?> atencionesMedicas) {
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

    public List<?> getAtencionesMedicas() {
        return atencionesMedicas;
    }

    public void setAtencionesMedicas(List<?> atencionesMedicas) {
        this.atencionesMedicas = atencionesMedicas;
    }
}

