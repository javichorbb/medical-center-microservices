package clinica.microhistoriaclinicacompuesto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AtencionMedica {

    private Long idAtencion;
    private Long idCita;
    private Long idHistoria;
    private LocalDate fecha;
    private LocalTime hora;
    private String diagnostico;
    private String tratamiento;
    private Medico medico;
    private List<RecetaMedica> recetaMedica;
    private List<AnalisisClinico> analisisClinicos;

    public AtencionMedica() {
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public List<RecetaMedica> getRecetaMedica() {
        return recetaMedica;
    }

    public void setRecetaMedica(List<RecetaMedica> recetaMedica) {
        this.recetaMedica = recetaMedica;
    }

    public List<AnalisisClinico> getAnalisisClinicos() {
        return analisisClinicos;
    }

    public void setAnalisisClinicos(List<AnalisisClinico> analisisClinicos) {
        this.analisisClinicos = analisisClinicos;
    }
}
