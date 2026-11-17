package clinica.microhistoriaclinicacompuesto;

import java.time.LocalDate;
import java.util.List;

public class Salida {

    // 1. Datos generales de la Historia Clínica
    private Long idHistoria;
    private LocalDate fechaApertura;

    // 2. Objeto 1: Enfermera que aperturó la historia (de MicroValidarEnfermera)
    private Enfermera enfermera;

    // 3. Objeto 2: Paciente titular de la historia (de MicroPaciente)
    private Paciente paciente;

    // 4. Objeto 3: Lista de Atenciones Médicas registradas (de MicroAtencionMedica)
    private List<AtencionMedica> atencionesMedicas;

    public Salida() {
    }

    public Salida(Long idHistoria, LocalDate fechaApertura, Enfermera enfermera, Paciente paciente, List<AtencionMedica> atencionesMedicas) {
        this.idHistoria = idHistoria;
        this.fechaApertura = fechaApertura;
        this.enfermera = enfermera;
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

    public Enfermera getEnfermera() {
        return enfermera;
    }

    public void setEnfermera(Enfermera enfermera) {
        this.enfermera = enfermera;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<AtencionMedica> getAtencionesMedicas() {
        return atencionesMedicas;
    }

    public void setAtencionesMedicas(List<AtencionMedica> atencionesMedicas) {
        this.atencionesMedicas = atencionesMedicas;
    }
}
