package com.sfg.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleConfig {
    // Example of routing requests to Google from localhost.
    @Bean
    public RouteLocator googleRouteConfig(RouteLocatorBuilder builder){
        return builder.routes()
                // http://localhost:9090/googlesearch -> redirect to https://google.com
                .route(r -> r.path("/googlesearch")
                        // rewrite the path so that '/googlesearch' is slashed from the final redirect uri
                        .filters(f -> f.rewritePath("/googlesearch(<segment>/?.*)", "${segment}"))
                .uri("https://google.com")
                .id("google"))
                .build();
    }
}
