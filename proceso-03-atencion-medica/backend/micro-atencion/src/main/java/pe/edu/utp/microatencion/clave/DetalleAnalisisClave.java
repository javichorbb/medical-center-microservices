package pe.edu.utp.microatencion.clave;

import java.io.Serializable;
import java.util.Objects;

public class DetalleAnalisisClave implements Serializable {

    private Long idAtencion;
    private Long idAnalisis;

    public DetalleAnalisisClave() {
    }

    public DetalleAnalisisClave(Long idAtencion, Long idAnalisis) {
        this.idAtencion = idAtencion;
        this.idAnalisis = idAnalisis;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DetalleAnalisisClave otra)) {
            return false;
        }
        return Objects.equals(idAtencion, otra.idAtencion)
            && Objects.equals(idAnalisis, otra.idAnalisis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtencion, idAnalisis);
    }
}
