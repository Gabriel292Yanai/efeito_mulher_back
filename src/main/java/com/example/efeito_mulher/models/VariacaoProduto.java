package com.example.efeito_mulher.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class VariacaoProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cor;       // ex. "#000000"
    private String tamanho;   // P, M, G…
    private Integer quantidade;

    /* FK exposta no JSON: */
    @Column(name = "produto_id")
    private Long produtoId;

    /* Relacionamento JPA, mas ignorado na serialização: */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    @JsonIgnore
    private Produto produto;
}