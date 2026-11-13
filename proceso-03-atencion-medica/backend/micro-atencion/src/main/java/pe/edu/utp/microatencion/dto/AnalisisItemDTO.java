package pe.edu.utp.microatencion.dto;

public class AnalisisItemDTO {

    private Long idAnalisis;
    private String indicacion;

    public AnalisisItemDTO() {
    }

    public AnalisisItemDTO(Long idAnalisis, String indicacion) {
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
