package com.loja.lojaeletronicos.controller;

import com.loja.lojaeletronicos.model.Categoria;
import com.loja.lojaeletronicos.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public Categoria criar(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @PostMapping("/lista")
    public List<Categoria> criarLista(@RequestBody List<Categoria> categorias) {
        return categoriaRepository.saveAll(categorias);
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria dados) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNome(dados.getNome());
                    return ResponseEntity.ok(categoriaRepository.save(categoria));
                }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> atualizarParcial(@PathVariable Long id, @RequestBody Categoria dados) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    if (dados.getNome() != null) categoria.setNome(dados.getNome());
                    return ResponseEntity.ok(categoriaRepository.save(categoria));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public void deletarTodas() {
        categoriaRepository.deleteAll();
    }
}