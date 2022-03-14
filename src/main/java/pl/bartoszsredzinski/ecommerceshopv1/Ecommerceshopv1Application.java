package pl.bartoszsredzinski.ecommerceshopv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import pl.bartoszsredzinski.ecommerceshopv1.config.SwaggerConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
public class Ecommerceshopv1Application{

    public static void main(String[] args){
        SpringApplication.run(Ecommerceshopv1Application.class, args);
    }

}
