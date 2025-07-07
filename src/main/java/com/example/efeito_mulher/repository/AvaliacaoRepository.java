package com.example.efeito_mulher.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.efeito_mulher.models.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByProdutoId(Long produtoId);
}
