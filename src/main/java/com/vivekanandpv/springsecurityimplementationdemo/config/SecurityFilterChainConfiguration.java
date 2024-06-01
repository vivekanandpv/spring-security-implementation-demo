package com.vivekanandpv.springsecurityimplementationdemo.config;

import com.vivekanandpv.springsecurityimplementationdemo.auth.AppJwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityFilterChainConfiguration {
    private final AppJwtAuthenticationFilter requestFilter;

    public SecurityFilterChainConfiguration(AppJwtAuthenticationFilter requestFilter) {
        this.requestFilter = requestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.addFilterAt(requestFilter, BasicAuthenticationFilter.class).build();
    }
}
