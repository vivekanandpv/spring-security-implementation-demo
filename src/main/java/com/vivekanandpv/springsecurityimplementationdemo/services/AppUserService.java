package com.vivekanandpv.springsecurityimplementationdemo.services;

import com.vivekanandpv.springsecurityimplementationdemo.models.AppRole;
import com.vivekanandpv.springsecurityimplementationdemo.models.AppUser;
import com.vivekanandpv.springsecurityimplementationdemo.viewmodels.UserRegisterViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AppUserService extends UserDetailsService {
    boolean usernameExists(String username);

    AppUser createUser(UserRegisterViewModel viewModel);

    Optional<AppUser> findUser(String username);

    Optional<AppUser> findUserById(int id);

    Set<AppRole> getRolesByUsername(String username);

    List<AppUser> getAllUsers();
}
