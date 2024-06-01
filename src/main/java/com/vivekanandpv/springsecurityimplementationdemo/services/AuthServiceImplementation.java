package com.vivekanandpv.springsecurityimplementationdemo.services;

import com.vivekanandpv.springsecurityimplementationdemo.exceptions.GeneralAuthenticationException;
import com.vivekanandpv.springsecurityimplementationdemo.exceptions.LoginFailedException;
import com.vivekanandpv.springsecurityimplementationdemo.exceptions.RecordNotFoundException;
import com.vivekanandpv.springsecurityimplementationdemo.models.AppRole;
import com.vivekanandpv.springsecurityimplementationdemo.models.AppUser;
import com.vivekanandpv.springsecurityimplementationdemo.repositories.AppUserRepository;
import com.vivekanandpv.springsecurityimplementationdemo.utils.AppJwtUtils;
import com.vivekanandpv.springsecurityimplementationdemo.viewmodels.LoginViewModel;
import com.vivekanandpv.springsecurityimplementationdemo.viewmodels.TokenResponse;
import com.vivekanandpv.springsecurityimplementationdemo.viewmodels.UserListViewModel;
import com.vivekanandpv.springsecurityimplementationdemo.viewmodels.UserRegisterViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImplementation implements AuthService {
    private final AppUserService userService;
    private final AppJwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository userRepository;

    public AuthServiceImplementation(
            AppUserService userService,
            AppJwtUtils jwtUtils,
            PasswordEncoder passwordEncoder,
            AppUserRepository userRepository
    ) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public TokenResponse getToken(LoginViewModel viewModel) {
        AppUser user = userRepository.findUserByUsername(viewModel.getUsername())
                .orElseThrow(LoginFailedException::new);

        if (!passwordEncoder.matches(viewModel.getPassword(), user.getPassword())) {
            throw new LoginFailedException();
        }

        String token = jwtUtils.generateToken(userService
                .loadUserByUsername(viewModel.getUsername()));

        return new TokenResponse(token);
    }

    @Override
    public void register(UserRegisterViewModel viewModel) {
        if (this.userService.usernameExists(viewModel.getUsername())) {
            throw new GeneralAuthenticationException();
        }

        AppUser user = this.userService.createUser(viewModel);
    }

    @Override
    public List<UserListViewModel> getAllUsers() {
        return this.userService
                .getAllUsers()
                .stream()
                .map(u -> {
                    UserListViewModel viewModel = new UserListViewModel();
                    BeanUtils.copyProperties(u, viewModel);
                    viewModel.setRoles(u.getAppRoles().stream().map(AppRole::getRole).collect(Collectors.toList()));

                    return viewModel;
                })
                .sorted(Comparator.comparingInt(UserListViewModel::getAppUserId))
                .collect(Collectors.toList());
    }

    @Override
    public UserListViewModel getUserById(int id) {
        AppUser user = this.userService.findUserById(id)
                .orElseThrow(RecordNotFoundException::new);

        UserListViewModel viewModel = new UserListViewModel();
        BeanUtils.copyProperties(user, viewModel);
        viewModel.setRoles(user.getAppRoles().stream().map(AppRole::getRole).collect(Collectors.toList()));
        return viewModel;
    }
}
