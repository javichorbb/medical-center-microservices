package pe.edu.utp.microatencion.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RegistroAtencionDTO {

    private Long idCita;
    private Long idHistoria;
    private Long idMedico;
    private LocalDate fecha;
    private LocalTime hora;
    private String diagnostico;
    private String tratamiento;

    private List<MedicamentoItemDTO> listaMedicamentos;
    private List<AnalisisItemDTO> listaAnalisis;

    public RegistroAtencionDTO() {
    }

    public RegistroAtencionDTO(Long idCita, Long idHistoria, Long idMedico,
                               LocalDate fecha, LocalTime hora,
                               String diagnostico, String tratamiento,
                               List<MedicamentoItemDTO> listaMedicamentos,
                               List<AnalisisItemDTO> listaAnalisis) {
        this.idCita = idCita;
        this.idHistoria = idHistoria;
        this.idMedico = idMedico;
        this.fecha = fecha;
        this.hora = hora;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.listaMedicamentos = listaMedicamentos;
        this.listaAnalisis = listaAnalisis;
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

    public List<MedicamentoItemDTO> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void setListaMedicamentos(List<MedicamentoItemDTO> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

    public List<AnalisisItemDTO> getListaAnalisis() {
        return listaAnalisis;
    }

    public void setListaAnalisis(List<AnalisisItemDTO> listaAnalisis) {
        this.listaAnalisis = listaAnalisis;
    }
}
