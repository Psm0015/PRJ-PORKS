package com.Porks.Porks.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Porks.Porks.Entity.Carrinho;
import com.Porks.Porks.Entity.Produto;
import com.Porks.Porks.Entity.Usuario;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer>{
    public List<Carrinho> findByUsuario(Usuario usuario);
    public void deleteByProduto(Produto produto);
}
