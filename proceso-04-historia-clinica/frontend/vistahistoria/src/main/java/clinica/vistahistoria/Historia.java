package clinica.vistahistoria;

public class Historia {
    private String idHistoria;
    private String idPaciente;
    private String fecha;
    private String diagnostico;
    private String tratamiento;

    public Historia() {}

    public Historia(String idHistoria, String idPaciente, String fecha, String diagnostico, String tratamiento) {
        this.idHistoria = idHistoria;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public String getIdHistoria() { return idHistoria; }
    public void setIdHistoria(String idHistoria) { this.idHistoria = idHistoria; }

    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
}