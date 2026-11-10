package Citamedica.VistaCitaMedica;

public class Paciente {
    private Long idPaciente;
    private String nombre;
    private String dni;

    public Paciente() {}
    public Paciente(Long idPaciente, String nombre, String dni) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.dni = dni;
    }

    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
}