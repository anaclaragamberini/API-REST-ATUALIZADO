package com.loja.lojaeletronicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.lojaeletronicos.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}