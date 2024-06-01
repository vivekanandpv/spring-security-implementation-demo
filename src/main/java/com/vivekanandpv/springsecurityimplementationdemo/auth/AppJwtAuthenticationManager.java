package com.vivekanandpv.springsecurityimplementationdemo.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AppJwtAuthenticationManager implements AuthenticationManager {
    private final AuthenticationProvider authenticationProvider;

    public AppJwtAuthenticationManager(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return this.authenticationProvider.authenticate(authentication);
    }
}
