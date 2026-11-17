package ProgramacionHorario.MicroCompProgramacionHorario;

import java.time.LocalDate;
import java.util.List;

public class Salida {

    // 1. Datos generales de la Programación de Horario
    private Long idProgramacion;
    private LocalDate fecha;
    private String consultorio;
    private String estado;

    // 2. Objeto 1: Usuario administrativo que elaboró la programación (de
    // MicroValidarUsuario)
    private Usuario usuario;

    // 3. Objeto 2: Médico asignado con su especialidad (de MicroMedico)
    private Medico medico;

    // 4. Objeto 3: Lista de Franjas Horarias de atención de 20 min (de
    // MicroFranjaHoraria)
    private List<FranjaHoraria> franjasHorarias;

    public Salida() {
    }

    public Salida(Long idProgramacion, LocalDate fecha, String consultorio, String estado, Usuario usuario,
            Medico medico, List<FranjaHoraria> franjasHorarias) {
        this.idProgramacion = idProgramacion;
        this.fecha = fecha;
        this.consultorio = consultorio;
        this.estado = estado;
        this.usuario = usuario;
        this.medico = medico;
        this.franjasHorarias = franjasHorarias;
    }

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public List<FranjaHoraria> getFranjasHorarias() {
        return franjasHorarias;
    }

    public void setFranjasHorarias(List<FranjaHoraria> franjasHorarias) {
        this.franjasHorarias = franjasHorarias;
    }
}
