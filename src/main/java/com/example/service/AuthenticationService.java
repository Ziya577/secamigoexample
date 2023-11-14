package com.example.service;

import com.example.auth.AuthenticationResponse;
import com.example.auth.AuthenticationrRequest;
import com.example.auth.RegisterRequest;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .accountNumber(request.getAccountNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .balance(request.getBalance())
                .role(Role.USER)


                .build();
        userRepository.save(user);
        var jwtToken=jwtService.generatetoken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }

    public AuthenticationResponse authenticate(AuthenticationrRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

 var jwtToken=jwtService.generatetoken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
