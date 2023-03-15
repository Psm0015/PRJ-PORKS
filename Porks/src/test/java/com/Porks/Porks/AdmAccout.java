package com.Porks.Porks;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Porks.Porks.Entity.Role;
import com.Porks.Porks.Entity.Usuario;
import com.Porks.Porks.Repositories.UserRepository;

public class AdmAccout {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository repository;


    @Test
	public void admaccount(){
		Usuario user =  new Usuario();
        user.setNome("Pedro Santos da Mota");
        user.setEmail("pedro.ppssmm@gmail.com");
        user.setCpf("07452056159");
        user.setNascimento(new Date(2003-06-29));
        user.setSenha(passwordEncoder.encode("123010203"));
        user.setRole(Role.ADMIN);
		repository.save(user);
	}
}
