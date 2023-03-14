package com.Porks.Porks.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Porks.Porks.Objects.AuthenticationRequest;
import com.Porks.Porks.Objects.AuthenticationResponse;
import com.Porks.Porks.Objects.RegisterRequest;
import com.Porks.Porks.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
        .nome(request.getNome())
        .email(request.getEmail())
        .cpf(request.getCpf())
        .nascimento(request.getNascimento())
        .senha(passwordEncoder.encode(request.getSenha()))
        .build();
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }

}
