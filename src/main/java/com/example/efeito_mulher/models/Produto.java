package com.example.efeito_mulher.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(nullable = true)
    private String categoria;
    @Column(nullable = true)
    private String subcategoria;

    @Column(length = 1200)
    private String descricao;

    private BigDecimal preco;         // preço “cheio”
    private BigDecimal precoPix;      // –5 %
    private BigDecimal precoParcela;  // 1/6 do preço

    private String imagem;            // thumb principal

    /* galeria de imagens e detalhes em listas simples */
    @ElementCollection private List<String> imagens;
    @ElementCollection private List<String> detalhes;

    /* Variações */
    @OneToMany(mappedBy = "produto",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<VariacaoProduto> variacoes;
}