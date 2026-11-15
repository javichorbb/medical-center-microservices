package pe.edu.utp.microatencion;

import java.util.List;

public class ResultadoAtencion {

    private AtencionMedica atencion;
    private List<DetalleReceta> receta;
    private List<DetalleAnalisis> analisis;

    public ResultadoAtencion() {
    }

    public ResultadoAtencion(AtencionMedica atencion,
                             List<DetalleReceta> receta,
                             List<DetalleAnalisis> analisis) {
        this.atencion = atencion;
        this.receta = receta;
        this.analisis = analisis;
    }

    public AtencionMedica getAtencion() {
        return atencion;
    }

    public void setAtencion(AtencionMedica atencion) {
        this.atencion = atencion;
    }

    public List<DetalleReceta> getReceta() {
        return receta;
    }

    public void setReceta(List<DetalleReceta> receta) {
        this.receta = receta;
    }

    public List<DetalleAnalisis> getAnalisis() {
        return analisis;
    }

    public void setAnalisis(List<DetalleAnalisis> analisis) {
        this.analisis = analisis;
    }
}
