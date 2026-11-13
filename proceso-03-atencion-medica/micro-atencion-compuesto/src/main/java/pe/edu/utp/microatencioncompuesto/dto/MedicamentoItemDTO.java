package pe.edu.utp.microatencioncompuesto.dto;

public class MedicamentoItemDTO {

    private Long idMedicamento;
    private String dosis;
    private Integer cantidad;

    public MedicamentoItemDTO() {
    }

    public MedicamentoItemDTO(Long idMedicamento, String dosis, Integer cantidad) {
        this.idMedicamento = idMedicamento;
        this.dosis = dosis;
        this.cantidad = cantidad;
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
