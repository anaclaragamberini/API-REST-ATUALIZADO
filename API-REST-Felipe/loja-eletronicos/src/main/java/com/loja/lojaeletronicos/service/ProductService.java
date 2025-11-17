package com.loja.lojaeletronicos.service;

import com.loja.lojaeletronicos.model.Produto;
import com.loja.lojaeletronicos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProdutoRepository repository;

    public ProductService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto create(Produto product) {
        return repository.save(product);
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}