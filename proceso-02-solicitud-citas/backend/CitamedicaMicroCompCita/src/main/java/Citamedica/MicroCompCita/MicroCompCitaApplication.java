package Citamedica.MicroCompCita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroCompCitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroCompCitaApplication.class, args);
	}
        @Bean
        @LoadBalanced
        public RestTemplate rt(){
            return new RestTemplate();
        }
}
