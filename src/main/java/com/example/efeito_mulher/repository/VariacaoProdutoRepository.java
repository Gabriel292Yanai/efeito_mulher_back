package com.example.efeito_mulher.repository;

import com.example.efeito_mulher.models.Produto;
import com.example.efeito_mulher.models.VariacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariacaoProdutoRepository extends JpaRepository<VariacaoProduto, Long> {
    void deleteAllByProduto(Produto produto);
}
