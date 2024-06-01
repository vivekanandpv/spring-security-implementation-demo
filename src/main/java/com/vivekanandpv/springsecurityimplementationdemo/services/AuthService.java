package com.vivekanandpv.springsecurityimplementationdemo.services;

import com.vivekanandpv.springsecurityimplementationdemo.viewmodels.LoginViewModel;
import com.vivekanandpv.springsecurityimplementationdemo.viewmodels.TokenResponse;
import com.vivekanandpv.springsecurityimplementationdemo.viewmodels.UserListViewModel;
import com.vivekanandpv.springsecurityimplementationdemo.viewmodels.UserRegisterViewModel;

import java.util.List;

public interface AuthService {
    TokenResponse getToken(LoginViewModel viewModel);
    void register(UserRegisterViewModel viewModel);
    List<UserListViewModel> getAllUsers();
    UserListViewModel getUserById(int id);
}
