package clinica.microhistoriaclinicacompuesto;

public class EntradaPaciente {

    private String dni;
    private String nombre;
    private String genero;
    private String telefono;

    public EntradaPaciente() {
    }

    public EntradaPaciente(String dni, String nombre, String genero, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.genero = genero;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

