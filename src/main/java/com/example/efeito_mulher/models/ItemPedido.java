package com.example.efeito_mulher.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produtoId;
    private String titulo;
    private String cor;
    private String tamanho;
    private Integer quantidade;
    private BigDecimal preco;
    private String imagem;

    @ManyToOne
    private Pedido pedido;
}
