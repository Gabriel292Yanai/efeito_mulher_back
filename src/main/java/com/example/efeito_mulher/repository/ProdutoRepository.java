package com.example.efeito_mulher.repository;

import com.example.efeito_mulher.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    List<Produto> findByCategoriaIgnoreCase(String categoria);
    List<Produto> findByCategoriaIgnoreCaseAndSubcategoriaIgnoreCase(String categoria, String subcategoria);
}
