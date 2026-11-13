package clinica.microhistoria;

public class SalidaPaciente {

    private Long idPaciente;
    private String nombre;
    private String dni;
    private String genero;
    private String telefono;

    public SalidaPaciente() {
    }

    public SalidaPaciente(Long idPaciente, String nombre, String dni, String genero, String telefono) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.dni = dni;
        this.genero = genero;
        this.telefono = telefono;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

