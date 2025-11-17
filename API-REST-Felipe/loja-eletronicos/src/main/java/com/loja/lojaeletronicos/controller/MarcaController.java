package com.loja.lojaeletronicos.controller;

import com.loja.lojaeletronicos.model.Marca;
import com.loja.lojaeletronicos.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;


    @PostMapping
    public Marca criarMarca(@RequestBody Marca marca) {
        return marcaRepository.save(marca);
    }

    @PostMapping("/lista")
    public List<Marca> criarLista(@RequestBody List<Marca> marcas) {
        return marcaRepository.saveAll(marcas);
    }


    @GetMapping
    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Marca buscarPorId(@PathVariable Long id) {
        return marcaRepository.findById(id).orElse(null);
    }


    @PutMapping("/{id}")
    public Marca atualizarMarca(@PathVariable Long id, @RequestBody Marca marca) {
        marca.setId(id);
        return marcaRepository.save(marca);
    }

    @PatchMapping("/{id}")
    public Marca atualizarParcial(@PathVariable Long id, @RequestBody Marca dados) {
        Optional<Marca> marcaOp = marcaRepository.findById(id);

        if (marcaOp.isPresent()) {
            Marca marca = marcaOp.get();

            if (dados.getNome() != null) {
                marca.setNome(dados.getNome());
            }

            return marcaRepository.save(marca);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Long id) {
        marcaRepository.deleteById(id);
    }

    @DeleteMapping
    public void deletarTodas() {
        marcaRepository.deleteAll();
    }
}