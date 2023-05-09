package com.Porks.Porks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Porks.Porks.Entity.Produto;
import com.Porks.Porks.Entity.Usuario;
import com.Porks.Porks.Repositories.CarrinhoRepository;
import com.Porks.Porks.Repositories.ProdutoRepository;
import com.Porks.Porks.Repositories.UserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProdutoRepository pRepository;

    @Autowired
    private CarrinhoRepository cRepository;

    @Autowired
    private UserRepository uRepository;

    //////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// Usuários/////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @GetMapping("/listall")
    public ResponseEntity<Iterable<Usuario>> findAllPrds() {
        try {
            return ResponseEntity.status(200).body(uRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
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

    @DeleteMapping("/apgruser/{id}")
    public ResponseEntity<String> apgruser(@PathVariable Integer id) {
        uRepository.deleteById(id);
        return ResponseEntity.ok().body("Usuário Deletado com Sucesso!");
    }

    //////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// Produtos/////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @PostMapping("/cadprd")
    public ResponseEntity<String> cadastrarProduto(@RequestBody Produto prd) {
        try {
            pRepository.save(prd);
            return ResponseEntity.status(200).body("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @DeleteMapping("/apgrprd/{id}")
    @Transactional
    public ResponseEntity<String> apgrprd(@PathVariable Integer id) {
        Produto prd = pRepository.findById(id).get();
        cRepository.deleteByProduto(prd);
        pRepository.deleteById(id);
        return ResponseEntity.ok().body("Produto Deletado com Sucesso!");
    }

    @PutMapping("/editarprd")
    public ResponseEntity<?> editarProduto(@RequestBody Produto prd) {
        try {
            Produto prdb = pRepository.findById(prd.getId()).get();
            prdb.setIngredientes(prd.getIngredientes());
            prdb.setPreco(prd.getPreco());
            prdb.setCategoria(prd.getCategoria());
            prdb.setNome(prd.getNome());
            pRepository.save(prdb);
            return ResponseEntity.ok().body("Produto editado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
