package clinica.microhistoriaclinicacompuesto;

public class AnalisisClinico {

    private Long idDetalleAnalisis;
    private Long idAnalisis;
    private String descripcion;
    private String tipo;
    private String indicacion;

    public AnalisisClinico() {
    }

    public Long getIdDetalleAnalisis() {
        return idDetalleAnalisis;
    }

    public void setIdDetalleAnalisis(Long idDetalleAnalisis) {
        this.idDetalleAnalisis = idDetalleAnalisis;
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
