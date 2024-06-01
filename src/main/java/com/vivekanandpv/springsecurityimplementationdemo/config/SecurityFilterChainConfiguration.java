package com.vivekanandpv.springsecurityimplementationdemo.config;

import com.vivekanandpv.springsecurityimplementationdemo.auth.AppJwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityFilterChainConfiguration {
    private final AppJwtAuthenticationFilter requestFilter;
    private final List<String> allowedOrigins;
    private final List<String> allowedMethods;
    private final List<String> allowedHeaders;
    private final String corsUrlPattern;
    private final boolean allowCredentials;
    private final long corsMaxAge;

    public SecurityFilterChainConfiguration(
            AppJwtAuthenticationFilter requestFilter,
            @Value("${app.cors.allowed.origins}") List<String> allowedOrigins,
            @Value("${app.cors.allowed.methods}") List<String> allowedMethods,
            @Value("${app.cors.allowed.headers}") List<String> allowedHeaders,
            @Value("${app.cors.url.pattern}") String corsUrlPattern,
            @Value("${app.cors.allow.credentials}") boolean allowCredentials,
            @Value("${app.cors.max.age}") long corsMaxAge
    ) {
        this.requestFilter = requestFilter;
        this.allowedOrigins = allowedOrigins;
        this.allowedMethods = allowedMethods;
        this.allowedHeaders = allowedHeaders;
        this.corsUrlPattern = corsUrlPattern;
        this.allowCredentials = allowCredentials;
        this.corsMaxAge = corsMaxAge;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAt(requestFilter, BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(allowedOrigins);
        corsConfiguration.setAllowedMethods(allowedMethods);
        corsConfiguration.setAllowCredentials(allowCredentials);
        corsConfiguration.setAllowedHeaders(allowedHeaders);
        corsConfiguration.setMaxAge(corsMaxAge);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(corsUrlPattern, corsConfiguration);
        return source;
    }
}
