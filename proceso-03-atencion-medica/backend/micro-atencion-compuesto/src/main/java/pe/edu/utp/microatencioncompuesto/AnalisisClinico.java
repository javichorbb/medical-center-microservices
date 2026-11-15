package pe.edu.utp.microatencioncompuesto;

public class AnalisisClinico {

    private Long idAnalisis;
    private String descripcion;
    private String tipo;

    public AnalisisClinico() {
    }

    public AnalisisClinico(Long idAnalisis, String descripcion, String tipo) {
        this.idAnalisis = idAnalisis;
        this.descripcion = descripcion;
        this.tipo = tipo;
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
}
