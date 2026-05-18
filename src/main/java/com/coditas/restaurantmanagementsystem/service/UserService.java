package com.coditas.restaurantmanagementsystem.service;

import com.coditas.restaurantmanagementsystem.JWTService;
import com.coditas.restaurantmanagementsystem.dto.user.LoginRequest;
import com.coditas.restaurantmanagementsystem.dto.user.LoginResponse;
import com.coditas.restaurantmanagementsystem.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authmanager;
    private final JWTService jwtService;

    public LoginResponse login(LoginRequest request) {

        log.info("Authentication attempt from '{}' with password '{}'", request.getUsername(), request.getPassword());

        Authentication authentication =
                authmanager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();

            String accessToken = jwtService.generateAccessToken(user);

            return LoginResponse.builder()
                    .accesstoken(accessToken)
                    .build();
        } else {
            log.error("Invalid credentials for '{}' ",request.getUsername());
            throw new UsernameNotFoundException("User does not found for id");
        }
    }
}
