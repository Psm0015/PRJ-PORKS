package com.Porks.Porks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Porks.Porks.Entity.Produto;
import com.Porks.Porks.Entity.Usuario;
import com.Porks.Porks.Objects.AuthenticationRequest;
import com.Porks.Porks.Objects.AuthenticationResponse;
import com.Porks.Porks.Objects.RegisterRequest;
import com.Porks.Porks.Repositories.ProdutoRepository;
import com.Porks.Porks.Repositories.UserRepository;
import com.Porks.Porks.Service.AuthenticationService;

@RestController
public class GuestController {
    @Autowired
    private ProdutoRepository pRepository;
    
    private AuthenticationService service;

    @PostMapping("/registrar")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/listall")
    public ResponseEntity<Iterable<Produto>> buscarprds(){
        try {
            return ResponseEntity.status(200).body(pRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
