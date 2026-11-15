package pe.edu.utp.microatencioncompuesto;

public class SalidaAnalisisItem {

    private Long idAnalisis;
    private String descripcion;
    private String tipo;
    private String indicacion;

    public SalidaAnalisisItem() {
    }

    public SalidaAnalisisItem(Long idAnalisis, String descripcion, String tipo, String indicacion) {
        this.idAnalisis = idAnalisis;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.indicacion = indicacion;
    }

    public Long getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(Long idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }
}
