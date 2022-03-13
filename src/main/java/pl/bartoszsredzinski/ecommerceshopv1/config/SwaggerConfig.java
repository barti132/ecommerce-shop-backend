package pl.bartoszsredzinski.ecommerceshopv1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * Spring fox configuration
 *
 * @author Bartosz Średziński
 * created on 12.03.2022
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{
    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo())
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.bartoszsredzinski.ecommerceshopv1.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder().title("Ecommerce shop API")
                .version("1.0")
                .description("API for ecommerce shop application.\nExample username and password - user1/user1." +
                        " To authorize sign in with /auth/login, copy the jwt and past in authorize option with prefix Bearer.")
                .contact(new Contact("Bartosz Średziński", "http://bartoszsredzinski.pl", "barteksredzinski@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    private ApiKey apiKey(){
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }

}
