package pe.edu.utp.microatencioncompuesto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EntradaAtencion {

    private Long idCita;
    private Long idMedico;
    private Long idHistoria;
    private LocalDate fecha;
    private LocalTime hora;
    private String diagnostico;
    private String tratamiento;
    private List<EntradaReceta> listaMedicamentos;
    private List<EntradaAnalisis> listaAnalisis;

    public EntradaAtencion() {
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
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

    public List<EntradaReceta> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void setListaMedicamentos(List<EntradaReceta> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

    public List<EntradaAnalisis> getListaAnalisis() {
        return listaAnalisis;
    }

    public void setListaAnalisis(List<EntradaAnalisis> listaAnalisis) {
        this.listaAnalisis = listaAnalisis;
    }
}
