package com.Porks.Porks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Porks.Porks.Entity.Role;
import com.Porks.Porks.Entity.Usuario;
import com.Porks.Porks.Objects.AuthenticationRequest;
import com.Porks.Porks.Objects.AuthenticationResponse;
import com.Porks.Porks.Objects.RegisterRequest;
import com.Porks.Porks.Repositories.UserRepository;
import com.Porks.Porks.Security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Usuario user =  new Usuario();
        user.setNome(request.getNome());
        user.setEmail(request.getEmail());
        user.setCpf(request.getCpf());
        user.setNascimento(request.getNascimento());
        user.setSenha(passwordEncoder.encode(request.getSenha()));
        System.out.println(repository.countusers());
        if(repository.countusers() == 0){
            user.setRole(Role.ADMIN);
        }else{
            user.setRole(Role.USER);
        }
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getCpf(),
                request.getSenha())
        );
        var user  = repository.findByCpf(request.getCpf()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
