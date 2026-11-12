package ProgramacionHorario.VistaValidar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VistaValidarApplication {

    public static void main(String[] args) {
        SpringApplication.run(VistaValidarApplication.class, args);
    }

    @Bean
    public RestTemplate rt() {
        return new RestTemplate();
    }
}
