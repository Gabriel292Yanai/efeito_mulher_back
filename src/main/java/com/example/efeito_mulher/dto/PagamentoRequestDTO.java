package com.example.efeito_mulher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoRequestDTO {
    private BigDecimal total;
    private BigDecimal frete;
    private List<ItemCarrinhoDTO> itens;
    private Integer parcelas;
    private String numero;
    private String nome;
    private String validade;
    private String cvv;
}
