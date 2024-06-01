package com.vivekanandpv.springsecurityimplementationdemo.config;

import com.vivekanandpv.springsecurityimplementationdemo.auth.AppJwtAuthenticationFilter;
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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityFilterChainConfiguration {
    private final AppJwtAuthenticationFilter requestFilter;

    public SecurityFilterChainConfiguration(AppJwtAuthenticationFilter requestFilter) {
        this.requestFilter = requestFilter;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAt(requestFilter, BasicAuthenticationFilter.class)
                .build();
    }
}
