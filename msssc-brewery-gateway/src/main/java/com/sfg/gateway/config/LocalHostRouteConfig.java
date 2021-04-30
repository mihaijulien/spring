package com.sfg.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalHostRouteConfig {

    @Bean
    public RouteLocator localhostRoutes(RouteLocatorBuilder builder){

        // eg. http://localhost:9090/api/v2/beer/beer
        // http://localhost:9090/api/v2/beer/0a818933-087d-47f2-ad83-2f986ed087eb
        return builder.routes()
                .route(r -> r.path("/api/v2/beer*", "/api/v2/beer/*")
                        .uri("http://localhost:8080")
                        .id("beer-service"))
                .route(r -> r.path("/api/v1/customers/**")
                        .uri("http://localhost:8081")
                        .id("order-service"))
                .route(r -> r.path("/api/v1/beer/*/inventory")
                        .uri("http://localhost:8082")
                        .id("inventory-service"))
                .build();
    }
}
