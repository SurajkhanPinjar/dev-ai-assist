package com.aidevassist.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ai-core", r -> r.path("/ai/**")
                        .uri("http://localhost:8081")) // Replace with ai-core service port
                .route("bugfinder", r -> r.path("/bugs/**")
                        .uri("http://localhost:8082"))
                .route("code-optimizer", r -> r.path("/optimize/**")
                        .uri("http://localhost:8083"))
                .build();
    }
}
