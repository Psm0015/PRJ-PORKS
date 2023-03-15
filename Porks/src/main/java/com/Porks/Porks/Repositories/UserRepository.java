package com.Porks.Porks.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Porks.Porks.Entity.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByCpf(String cpf);

    @Query("SELECT COUNT(u) FROM Usuario u") Long countusers();

}
