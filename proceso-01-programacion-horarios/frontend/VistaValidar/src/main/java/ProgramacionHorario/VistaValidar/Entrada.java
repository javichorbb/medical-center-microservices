package ProgramacionHorario.VistaValidar;

public class Entrada {

    private Long idPersonal;
    private String pas;

    public Entrada() {
    }

    public Entrada(Long idPersonal, String pas) {
        this.idPersonal = idPersonal;
        this.pas = pas;
    }

    public Long getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Long idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }
}
