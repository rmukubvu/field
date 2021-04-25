package za.co.amakosifire.field;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FieldApplication {

    public static void main(String[] args) {
        SpringApplication.run(FieldApplication.class, args);
    }

}
