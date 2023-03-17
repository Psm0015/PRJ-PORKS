package com.Porks.Porks.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Produto> produtos;

    private int quantidade;

    public Carrinho(Usuario usuario, Produto produto, int quantidade) {
        this.usuario = usuario;
        this.produtos = new ArrayList<>();
        this.produtos.add(produto);
        this.quantidade = quantidade;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        this.produtos.add(produto);
        this.quantidade += quantidade;
    }

    public void removerProduto(Produto produto, int quantidade) {
        this.produtos.remove(produto);
        this.quantidade -= quantidade;
    }

}
