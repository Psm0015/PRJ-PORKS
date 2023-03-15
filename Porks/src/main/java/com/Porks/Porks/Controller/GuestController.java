package com.Porks.Porks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Porks.Porks.Entity.Produto;
import com.Porks.Porks.Repositories.ProdutoRepository;

@RestController
@RequestMapping("/convidado")
public class GuestController {

    @Autowired
    private ProdutoRepository pRepository;

    @GetMapping("/listall")
    public ResponseEntity<Iterable<Produto>> buscarprds(){
        try {
            return ResponseEntity.status(200).body(pRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
}
