package pe.edu.utp.microatencion.clave;

import java.io.Serializable;
import java.util.Objects;

public class DetalleRecetaClave implements Serializable {

    private Long idAtencion;
    private Long idMedicamento;

    public DetalleRecetaClave() {
    }

    public DetalleRecetaClave(Long idAtencion, Long idMedicamento) {
        this.idAtencion = idAtencion;
        this.idMedicamento = idMedicamento;
    }

    public Long getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(Long idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Long getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DetalleRecetaClave otra)) {
            return false;
        }
        return Objects.equals(idAtencion, otra.idAtencion)
            && Objects.equals(idMedicamento, otra.idMedicamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtencion, idMedicamento);
    }
}
