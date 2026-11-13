package clinica.vistavalidar;

public class Enfermera {
    private String idEnfermera;
    private String dni;
    private String nombre;
    private String apellido;
    private String pass;

    public Enfermera() {}

    public String getIdEnfermera() { return idEnfermera; }
    public void setIdEnfermera(String idEnfermera) { this.idEnfermera = idEnfermera; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }
}