package com.vivekanandpv.springsecurityimplementationdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class GeneralBeanConfiguration {
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        //  https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html
        //  https://stackoverflow.com/a/75691338/3969961
        //  https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-bcrypt
        return new BCryptPasswordEncoder(11);
    }
}
