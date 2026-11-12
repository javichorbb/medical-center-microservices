package ProgramacionHorario.VistaValidar;

public class Usuario {

    private Long idUsu;
    private String nom;
    private String pas;

    public Usuario() {
    }

    public Usuario(Long idUsu, String nom, String pas) {
        this.idUsu = idUsu;
        this.nom = nom;
        this.pas = pas;
    }

    public Long getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Long idUsu) {
        this.idUsu = idUsu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }
}
