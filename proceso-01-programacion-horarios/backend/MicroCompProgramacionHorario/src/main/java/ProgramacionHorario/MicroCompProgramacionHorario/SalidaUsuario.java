package ProgramacionHorario.MicroCompProgramacionHorario;

public class SalidaUsuario {

    private Long idUsuario;
    private String nombre;
    private String username;
    private String rol;

    public SalidaUsuario() {
    }

    public SalidaUsuario(Long idUsuario, String nombre, String username, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.rol = rol;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

