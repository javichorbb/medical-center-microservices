package ProgramacionHorario.MicroCompProgramacionHorario;

import java.util.List;

public class SalidaMedicoProgramado {

    private Long idMedico;
    private String nombre;
    private String especialidad;
    private List<ProgramacionConFranjas> programacionesHorarias;

    public SalidaMedicoProgramado() {
    }

    public SalidaMedicoProgramado(Long idMedico, String nombre, String especialidad, List<ProgramacionConFranjas> programacionesHorarias) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.programacionesHorarias = programacionesHorarias;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<ProgramacionConFranjas> getProgramacionesHorarias() {
        return programacionesHorarias;
    }

    public void setProgramacionesHorarias(List<ProgramacionConFranjas> programacionesHorarias) {
        this.programacionesHorarias = programacionesHorarias;
    }
}
