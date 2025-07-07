package com.example.efeito_mulher.dto;

import com.example.efeito_mulher.models.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class PedidoResponseDTO {

    private Long id;
    private LocalDateTime data;
    private BigDecimal total;
    private BigDecimal frete;
    private String status;
    private String codigoRastreamento;
    private List<ProdutoPedidoDTO> produtos;

    // Construtor que converte entidade Pedido → DTO
    public PedidoResponseDTO(Pedido p) {
        this.id = p.getId();
        this.data = p.getData();
        this.frete = p.getFrete();
        this.total = p.getTotal();
        this.status = p.getStatus();
        this.codigoRastreamento = p.getCodigoRastreamento();

        // Converte os itens do pedido para ProdutoPedidoDTO
        this.produtos = p.getItens().stream()
                .map(ProdutoPedidoDTO::new)
                .collect(Collectors.toList());
    }
}