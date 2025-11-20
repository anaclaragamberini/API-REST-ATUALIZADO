package com.loja.lojaeletronicos.model;

import jakarta.persistence.*;

@Entity
public class Produto {

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String categoria;
    private String descricao;
    private int estoque;
    private double preco;

    public Produto() {}

    public Produto(String nome, String categoria, String descricao,
                   int estoque, double preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.estoque = estoque;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }


    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
}