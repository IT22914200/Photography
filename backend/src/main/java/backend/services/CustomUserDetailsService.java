package com.example.photographsystem.services;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.photographsystem.repositories.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository appUserRepository;

    public CustomUserDetailsService(UserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    
}