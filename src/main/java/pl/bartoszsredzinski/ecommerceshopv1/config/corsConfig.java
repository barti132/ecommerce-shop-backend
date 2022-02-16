package pl.bartoszsredzinski.ecommerceshopv1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author Bartosz Średziński
 *
 * This configuration class allows cors request from localhost:4200 - production config
 */
@Configuration
public class corsConfig{


    /**
     * @return WebMvcConfigurer - config with added mapping for localhost
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }
}
