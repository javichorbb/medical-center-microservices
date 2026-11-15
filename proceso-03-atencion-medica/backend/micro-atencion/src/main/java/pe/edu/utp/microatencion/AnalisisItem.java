package pe.edu.utp.microatencion;

public class AnalisisItem {

    private Long idAnalisis;
    private String indicacion;

    public AnalisisItem() {
    }

    public AnalisisItem(Long idAnalisis, String indicacion) {
        this.idAnalisis = idAnalisis;
        this.indicacion = indicacion;
    }

    public Long getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(Long idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }
}
