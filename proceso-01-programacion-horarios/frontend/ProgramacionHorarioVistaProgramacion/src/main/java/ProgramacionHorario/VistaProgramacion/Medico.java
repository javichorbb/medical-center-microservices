package ProgramacionHorario.VistaProgramacion;

public class Medico {

    private Long idMed;
    private String nom;
    private String especialidad;

    public Medico() {
    }

    public Medico(Long idMed, String nom, String especialidad) {
        this.idMed = idMed;
        this.nom = nom;
        this.especialidad = especialidad;
    }

    public Long getIdMed() {
        return idMed;
    }

    public void setIdMed(Long idMed) {
        this.idMed = idMed;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    
}
