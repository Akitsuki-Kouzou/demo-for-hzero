package com.example.hzeromanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/management/**")
                        .allowedOrigins("*") // Allow all origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
                        .allowedHeaders("*"); // Allow all headers
//                registry.addMapping("/api/management/**/fuzzy/**")
//                        .allowedOrigins("*") // Allow all origins
//                        .allowedMethods("GET") // Allow specific HTTP methods
//                        .allowedHeaders("*"); // Allow all headers
//                registry.addMapping("/api/management/fuzzy/**")
//                        .allowedOrigins("*") // Allow all origins
//                        .allowedMethods("GET") // Allow specific HTTP methods
//                        .allowedHeaders("*"); // Allow all headers
            }
        };
    }
}