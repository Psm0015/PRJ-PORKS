package com.Porks.Porks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Porks.Porks.Entity.Produto;
import com.Porks.Porks.Repositories.ProdutoRepository;

@RestController
@RequestMapping("/adm")
public class AdminController {
    
    @Autowired
    private ProdutoRepository pRepository;

    @PostMapping("/cadprd")
    public ResponseEntity<String> cadastrarProduto(@RequestBody Produto prd){
        try {
            pRepository.save(prd);
            return ResponseEntity.status(200).body("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

}
