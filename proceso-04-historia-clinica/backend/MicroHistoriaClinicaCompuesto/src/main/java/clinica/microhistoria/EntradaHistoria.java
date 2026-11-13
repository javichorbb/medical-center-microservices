package clinica.microhistoria;

import java.time.LocalDate;

public class EntradaHistoria {

    private Long idPaciente;
    private LocalDate fechaApertura;

    public EntradaHistoria() {
    }

    public EntradaHistoria(Long idPaciente, LocalDate fechaApertura) {
        this.idPaciente = idPaciente;
        this.fechaApertura = fechaApertura;
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

