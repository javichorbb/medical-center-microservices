package clinica.microvalidar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Enfermera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnfermera;
    private String nombre;
    private String dni;
    private String username;
    private String password;

    public Enfermera() {
    }

    public Enfermera(Long idEnfermera, String nombre, String dni, String username, String password) {
        this.idEnfermera = idEnfermera;
        this.nombre = nombre;
        this.dni = dni;
        this.username = username;
        this.password = password;
    }

    public Long getIdEnfermera() {
        return idEnfermera;
    }

    public void setIdEnfermera(Long idEnfermera) {
        this.idEnfermera = idEnfermera;
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
}
