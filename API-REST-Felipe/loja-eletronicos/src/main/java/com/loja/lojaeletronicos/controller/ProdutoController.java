package com.loja.lojaeletronicos.controller;

import com.loja.lojaeletronicos.model.Produto;
import com.loja.lojaeletronicos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PostMapping("/lote")
    public List<Produto> criarProdutos(@RequestBody List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Produto> buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto novoProduto) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(novoProduto.getNome());
            produto.setCategoria(novoProduto.getCategoria());
            produto.setDescricao(novoProduto.getDescricao());
            produto.setEstoque(novoProduto.getEstoque());
            produto.setPreco(novoProduto.getPreco());
            return produtoRepository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @PatchMapping("/{id}")
    public Produto atualizarParcial(@PathVariable Long id, @RequestBody Produto atualizacoes) {
        return produtoRepository.findById(id).map(produto -> {

            if (atualizacoes.getNome() != null) produto.setNome(atualizacoes.getNome());
            if (atualizacoes.getCategoria() != null) produto.setCategoria(atualizacoes.getCategoria());
            if (atualizacoes.getDescricao() != null) produto.setDescricao(atualizacoes.getDescricao());
            if (atualizacoes.getEstoque() != 0) produto.setEstoque(atualizacoes.getEstoque());
            if (atualizacoes.getPreco() != 0) produto.setPreco(atualizacoes.getPreco());

            return produtoRepository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }

    @DeleteMapping
    public void deletarTodos() {
        produtoRepository.deleteAll();
    }
}