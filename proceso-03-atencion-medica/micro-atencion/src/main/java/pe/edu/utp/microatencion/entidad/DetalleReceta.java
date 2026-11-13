package pe.edu.utp.microatencion.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import pe.edu.utp.microatencion.clave.DetalleRecetaClave;

@Entity
@IdClass(DetalleRecetaClave.class)
public class DetalleReceta {

    @Id
    private Long idAtencion;

    @Id
    private Long idMedicamento;

    private String dosis;
    private Integer cantidad;

    public DetalleReceta() {
    }

    public DetalleReceta(Long idAtencion, Long idMedicamento, String dosis, Integer cantidad) {
        this.idAtencion = idAtencion;
        this.idMedicamento = idMedicamento;
        this.dosis = dosis;
        this.cantidad = cantidad;
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

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
