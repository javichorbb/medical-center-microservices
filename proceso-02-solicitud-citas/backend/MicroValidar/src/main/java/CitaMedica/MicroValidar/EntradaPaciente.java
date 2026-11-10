
package CitaMedica.MicroValidar;

public class EntradaPaciente {
    private Long idPaciente;
    private String dni;

    public EntradaPaciente() {
    }

    public EntradaPaciente(Long idPaciente, String dni) {
        this.idPaciente = idPaciente;
        this.dni = dni;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    

}
