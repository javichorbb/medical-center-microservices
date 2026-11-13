package pe.edu.utp.microatencion.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class AtencionMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtencion;
    private Long idCita;
    private Long idHistoria;
    private Long idMedico;
    private LocalDate fecha;
    private LocalTime hora;
    private String diagnostico;
    private String tratamiento;

    public AtencionMedica() {
    }

    public AtencionMedica(Long idAtencion, Long idCita, Long idHistoria, Long idMedico,
                          LocalDate fecha, LocalTime hora, String diagnostico, String tratamiento) {
        this.idAtencion = idAtencion;
        this.idCita = idCita;
        this.idHistoria = idHistoria;
        this.idMedico = idMedico;
        this.fecha = fecha;
        this.hora = hora;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public Long getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Long idHistoria) {
        this.idHistoria = idHistoria;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
}
