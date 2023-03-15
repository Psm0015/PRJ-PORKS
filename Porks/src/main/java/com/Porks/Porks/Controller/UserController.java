package com.Porks.Porks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Porks.Porks.Entity.Usuario;
import com.Porks.Porks.Objects.EditRequest;
import com.Porks.Porks.Repositories.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository uRepository;

    @PutMapping("/editar")
    public ResponseEntity<String> teste(Authentication authentication, @RequestBody EditRequest request){
        Usuario user = (Usuario) authentication.getPrincipal();
        user.setCpf(request.getCpf());
        user.setNome(request.getNome());
        user.setNascimento(request.getNascimento());
        user.setEmail(request.getEmail());
        uRepository.save(user);

        return ResponseEntity.ok().body("Usuário Editado com sucesso!");
    }

}
