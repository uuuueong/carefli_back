package gradproject.carefli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CarefliApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarefliApplication.class, args);
    }

}
