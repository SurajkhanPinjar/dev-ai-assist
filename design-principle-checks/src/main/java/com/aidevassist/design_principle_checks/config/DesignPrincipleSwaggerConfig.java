package com.aidevassist.design_principle_checks.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DesignPrincipleSwaggerConfig {

    @Bean
    @Primary
    public OpenAPI optimizerOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Design Principle API")
                        .description("ai for Java code")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Dev AI Assist Project")
                        .url("https://github.com/your-repo"));
    }
}