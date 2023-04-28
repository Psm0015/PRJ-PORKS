package com.Porks.Porks.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Porks.Porks.Entity.Carrinho;
import com.Porks.Porks.Entity.Produto;
import com.Porks.Porks.Entity.Usuario;
import com.Porks.Porks.Objects.AuthenticationResponse;
import com.Porks.Porks.Objects.EditRequest;
import com.Porks.Porks.Repositories.CarrinhoRepository;
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
    CarrinhoRepository cRepository;

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

    @PostMapping("/carrinho")
    public ResponseEntity<?> adicionarProdutoAoCarrinho(Authentication authentication, @RequestParam Integer produtoId,
            @RequestParam int quantidade) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Produto produto = pRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Carrinho carrinho = new Carrinho();
        carrinho.setProduto(produto);
        carrinho.setQuantidade(quantidade);
        carrinho.setUsuario(usuario);

        cRepository.save(carrinho);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/carrinho")
    public ResponseEntity<?> vercarrinho(Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        Usuario usuario = uRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<Carrinho> carrinhos = cRepository.findByUsuario(usuario);
        return ResponseEntity.ok(carrinhos);
    }

    @DeleteMapping("/carrinho")
    public ResponseEntity<?> removerProdutoDoCarrinho(Authentication authentication, @RequestParam Integer iditem){
        try {
            cRepository.deleteById(iditem);
            return ResponseEntity.ok().body("Produto removido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

}
