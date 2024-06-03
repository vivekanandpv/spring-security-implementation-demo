package com.vivekanandpv.springsecurityimplementationdemo.auth;

import com.vivekanandpv.springsecurityimplementationdemo.utils.AppJwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class AppJwtAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final AppJwtUtils jwtUtils;

    public AppJwtAuthenticationProvider(UserDetailsService userDetailsService, AppJwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String token = String.valueOf(authentication.getCredentials());

            if (jwtUtils.validateToken(token)) {
                return new AppJwtAuthentication(
                        jwtUtils.extractUsername(token),
                        jwtUtils.extractClaim(token, c -> {
                            return ((ArrayList<String>) c.getPayload().get("roles"))
                                    .stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList());
                        }));
            }
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException exception) {
            throw new BadCredentialsException("Token invalid");
        }

        // There is an edge case where token is valid, but user might be blocked
        throw new BadCredentialsException("Login failed");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AppJwtAuthentication.class.equals(authentication);
    }
}
