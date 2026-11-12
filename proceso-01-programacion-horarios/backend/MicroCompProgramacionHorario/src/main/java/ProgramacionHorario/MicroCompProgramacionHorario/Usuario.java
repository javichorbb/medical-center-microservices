package ProgramacionHorario.MicroCompProgramacionHorario;

public class Usuario {

    private Long idUsuario;
    private String nombre;
    private String username;
    private String password;
    private String rol;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nombre, String username, String password, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

