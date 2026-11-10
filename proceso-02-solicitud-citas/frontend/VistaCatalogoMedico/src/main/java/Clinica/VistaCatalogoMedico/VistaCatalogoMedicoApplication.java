package Clinica.VistaCatalogoMedico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VistaCatalogoMedicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VistaCatalogoMedicoApplication.class, args);
    }

    // Bean necesario para inyectar RestTemplate con @Autowired en Servicio
    @Bean
    public RestTemplate rt() {
        return new RestTemplate();
    }
}
