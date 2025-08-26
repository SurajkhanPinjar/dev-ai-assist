package com.aidevassist.junit_generator.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestGenSwaggerConfig {

    @Bean
    public OpenAPI testGenOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("JUnit Test Generator API")
                        .description("AI-powered test generation for Java classes")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Dev AI Assist Project")
                        .url("https://github.com/your-repo"));
    }
}