package com.jpa.biblioteca.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración global de CORS (Cross-Origin Resource Sharing)
 * para permitir el acceso desde diferentes orígenes/dominios
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    public CorsConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todas las rutas
                .allowedOrigins(
                    "http://localhost:3000",    // React development server
                    "http://localhost:4200",    // Angular development server
                    "http://localhost:5173",    // Vite development server
                    "http://localhost:8080",
                    "http://localhost:5200", 
                    "http://127.0.0.1:5200",// Mismo servidor (para testing)
                    "https://midominio.com",    // Dominio de producción
                    "https://www.midominio.com"
                     // Dominio de producción con www
                )
                .allowedMethods(
                    "GET", 
                    "POST", 
                    "PUT", 
                    "DELETE", 
                    "PATCH", 
                    "OPTIONS"
                )
                .allowedHeaders(
                    "Origin",
                    "Content-Type",
                    "Accept",
                    "Authorization",
                    "X-Requested-With",
                    "Access-Control-Request-Method",
                    "Access-Control-Request-Headers"
                )
                .exposedHeaders(
                    "Access-Control-Allow-Origin",
                    "Access-Control-Allow-Credentials"
                )
                .allowCredentials(true) // Permite envío de cookies y credenciales
                .maxAge(3600); // Cache preflight por 1 hora
    }
}