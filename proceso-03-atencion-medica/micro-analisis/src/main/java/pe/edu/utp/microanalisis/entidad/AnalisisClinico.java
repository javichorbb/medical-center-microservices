package pe.edu.utp.microanalisis.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AnalisisClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
