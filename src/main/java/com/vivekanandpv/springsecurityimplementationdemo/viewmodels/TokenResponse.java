package com.vivekanandpv.springsecurityimplementationdemo.viewmodels;

public class TokenResponse {
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getJwt() {
        return token;
    }
}
