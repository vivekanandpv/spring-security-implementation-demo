package com.vivekanandpv.springsecurityimplementationdemo.viewmodels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginViewModel {
    @NotBlank
    @Size(min = 5, max = 50)
    private String username;

    @NotBlank
    @Size(min = 8, max = 50)
    private String password;

    public LoginViewModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
