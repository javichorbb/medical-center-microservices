package pe.edu.utp.microatencioncompuesto;

public class MedicamentoCesta {

    private Long idMedicamento;
    private String nombre;
    private String presentacion;
    private String dosis;
    private Integer cantidad;

    public MedicamentoCesta() {
    }

    public MedicamentoCesta(Long idMedicamento, String nombre, String presentacion, String dosis, Integer cantidad) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.dosis = dosis;
        this.cantidad = cantidad;
    }

    public Long getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
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
