package com.Porks.Porks.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Porks.Porks.Entity.ItemCarrinho;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Integer>{
    
}
