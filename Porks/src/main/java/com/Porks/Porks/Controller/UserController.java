package com.Porks.Porks.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Porks.Porks.Entity.ItemCarrinho;
import com.Porks.Porks.Entity.Produto;
import com.Porks.Porks.Entity.Usuario;
import com.Porks.Porks.Objects.AuthenticationResponse;
import com.Porks.Porks.Objects.EditRequest;
import com.Porks.Porks.Repositories.ProdutoRepository;
import com.Porks.Porks.Repositories.UserRepository;
import com.Porks.Porks.Security.JwtService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository uRepository;

    @Autowired
    ProdutoRepository pRepository;

    @Autowired
    private JwtService jwtService;

    @PutMapping("/editar")
    public ResponseEntity<AuthenticationResponse> teste(Authentication authentication,
            @RequestBody EditRequest request) {
        Usuario user = (Usuario) authentication.getPrincipal();
        user.setCpf(request.getCpf());
        user.setNome(request.getNome());
        user.setNascimento(request.getNascimento());
        user.setEmail(request.getEmail());
        uRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok().body(AuthenticationResponse.builder().token(jwtToken).build());
    }

    @GetMapping("/info")
    public ResponseEntity<Usuario> informarusuario(Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/addcarrinho")
    public ResponseEntity<String> addcarrinho(Authentication authentication, @RequestParam(value = "prdid") String prdid, @RequestParam(value = "qtd", defaultValue = "1") String qtd){
        try {
            Usuario user = (Usuario) authentication.getPrincipal();
            Produto prd = pRepository.findById(Integer.parseInt(prdid)).get();
            ItemCarrinho item = new ItemCarrinho();
            item.setProduto(prd);
            item.setQuantity(Integer.parseInt(qtd));
            item.setUsuario(user);
            user.getCarrinho().add(item);
            uRepository.save(user);
            return ResponseEntity.ok().body("Produto Inserido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/carrinho")
    public ResponseEntity<List<ItemCarrinho>> vercarrinho(Authentication authentication){
        Usuario user = (Usuario) authentication.getPrincipal();
        return ResponseEntity.ok().body(user.getCarrinho());
    }

}
