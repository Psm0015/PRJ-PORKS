package com.Porks.Porks.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Porks.Porks.Entity.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer>{
    
}
