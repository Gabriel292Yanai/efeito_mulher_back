package com.example.efeito_mulher.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoRequestDTO {
    private BigDecimal total;
    private BigDecimal frete;
    private List<ItemCarrinhoDTO> itens;
}