
package pe.edu.utp.microatencioncompuesto.dto;

import java.util.ArrayList;
import java.util.List;

public class SalidaAtencionDTO {

    private boolean valido;
    private String mensaje;
    private Long numAtencion;
    private List<String> errores = new ArrayList<>();

    public SalidaAtencionDTO() {
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getNumAtencion() {
        return numAtencion;
    }

    public void setNumAtencion(Long numAtencion) {
        this.numAtencion = numAtencion;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }
}
