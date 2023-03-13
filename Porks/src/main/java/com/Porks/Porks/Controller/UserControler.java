package com.Porks.Porks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Porks.Porks.Entity.Usuario;
import com.Porks.Porks.Repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class UserControler {

    @Autowired
    private UserRepository uRepository;

    @GetMapping("/listall")
    public ResponseEntity<Iterable<Usuario>> findAllPrds() {
        try {
            return ResponseEntity.status(200).body(uRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/novo")
    public ResponseEntity<String> newUser(@RequestBody Usuario user) {
        try {
            uRepository.save(user);
            return ResponseEntity.status(200).body("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<String> editUser(@RequestBody Usuario user) {
        if (uRepository.findById(user.getId()).get() != null) {
            try {
                uRepository.save(user);
                return ResponseEntity.status(200).body("Usuário cadastrado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(500).body(e.toString());
            }
        }
        return ResponseEntity.status(500).body(null);

    }

}
