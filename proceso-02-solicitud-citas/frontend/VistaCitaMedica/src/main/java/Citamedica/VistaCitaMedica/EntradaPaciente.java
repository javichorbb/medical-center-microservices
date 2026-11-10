package Citamedica.VistaCitaMedica;

public class EntradaPaciente {
    private String dni;

    public EntradaPaciente() {}
    public EntradaPaciente(String dni) {
        this.dni = dni;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
}