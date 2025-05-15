package com.example.photographsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import com.example.photographsystem.dtos.LoginDTO;
import com.example.photographsystem.dtos.OAuth2UserDto;
import com.example.photographsystem.models.User;
import com.example.photographsystem.repositories.UserRepository;
import com.example.photographsystem.security.JwtAuthResponse;
import com.example.photographsystem.security.JwtTokenProvider;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);

        Optional<User> user= userRepository.findByEmail(loginDto.getEmail());

        if(user.isEmpty()) return ResponseEntity.ok(new JwtAuthResponse(token, ""));
        if (user.get().isDeleteStatus()) {
            return ResponseEntity.status(403).body("Account has been deleted");
        }

        return ResponseEntity.ok(new JwtAuthResponse(token, user.get().getId()));
    }

    
}