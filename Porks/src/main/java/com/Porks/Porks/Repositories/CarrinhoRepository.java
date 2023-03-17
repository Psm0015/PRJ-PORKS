package com.Porks.Porks.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Porks.Porks.Entity.Carrinho;
import com.Porks.Porks.Entity.Usuario;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer>{
    public Carrinho findByUsuario(Usuario usuario);
}
