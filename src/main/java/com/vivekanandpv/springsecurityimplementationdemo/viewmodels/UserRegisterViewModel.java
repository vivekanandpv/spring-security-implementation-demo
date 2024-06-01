package com.vivekanandpv.springsecurityimplementationdemo.viewmodels;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserRegisterViewModel {
    @NotBlank
    @Email
    @Size(min = 5, max = 200)
    private String username;

    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Size(min = 8, max = 50)
    private String password;

    @NotNull
    private List<String> roles;

    public @NotBlank @Email @Size(min = 5, max = 200) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Email @Size(min = 5, max = 200) String username) {
        this.username = username;
    }

    public @NotBlank @Size(min = 3, max = 50) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank @Size(min = 3, max = 50) String firstName) {
        this.firstName = firstName;
    }

    public @Size(max = 50) String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(max = 50) String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank @Size(min = 8, max = 50) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 8, max = 50) String password) {
        this.password = password;
    }

    public @NotNull List<String> getRoles() {
        return roles;
    }

    public void setRoles(@NotNull List<String> roles) {
        this.roles = roles;
    }
}
