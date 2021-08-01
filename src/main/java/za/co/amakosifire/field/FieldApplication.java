package za.co.amakosifire.field;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import za.co.amakosifire.field.config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Slf4j
@Import(SwaggerConfiguration.class)
public class FieldApplication {

    public static void main(String[] args) {
        SpringApplication.run(FieldApplication.class, args);
    }

}
