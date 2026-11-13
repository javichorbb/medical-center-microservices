package pe.edu.utp.microcestaanalisis.dto;

public class AnalisisCestaDTO {

    private Long idAnalisis;
    private String indicacion;

    public AnalisisCestaDTO() {
    }

    public AnalisisCestaDTO(Long idAnalisis, String indicacion) {
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
