package com.coditas.restaurantmanagementsystem.service;

import com.coditas.restaurantmanagementsystem.entity.User;
import com.coditas.restaurantmanagementsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.ExceptionConstants;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = userRepository.findByUsername(username);
        Optional<User> userByEmail = userRepository.findByEmail(username);
        if(userByUsername.isPresent()) return userByUsername.get();
        else if(userByEmail.isPresent()) return userByEmail.get();
        else throw new UsernameNotFoundException("USERNAME_NOT_FOUND");
    }
}
