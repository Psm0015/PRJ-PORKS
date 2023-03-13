package com.Porks.Porks.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Porks.Porks.Entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
    
}
