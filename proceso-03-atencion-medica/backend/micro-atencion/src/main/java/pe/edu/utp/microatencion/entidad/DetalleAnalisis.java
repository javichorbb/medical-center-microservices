package pe.edu.utp.microatencion.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import pe.edu.utp.microatencion.clave.DetalleAnalisisClave;

@Entity
@IdClass(DetalleAnalisisClave.class)
public class DetalleAnalisis {

    @Id
    private Long idAtencion;

    @Id
    private Long idAnalisis;

    private String indicacion;

    public DetalleAnalisis() {
    }

    public DetalleAnalisis(Long idAtencion, Long idAnalisis, String indicacion) {
        this.idAtencion = idAtencion;
        this.idAnalisis = idAnalisis;
        this.indicacion = indicacion;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
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
