package com.example.efeito_mulher.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemCarrinhoDTO {
    private Long id;
    private String titulo;
    private Integer quantidade;
    private BigDecimal preco;
    private String cor;
    private String tamanho;
    private String imagem;
}