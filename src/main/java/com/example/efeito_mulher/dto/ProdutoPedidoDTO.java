package com.example.efeito_mulher.dto;

import com.example.efeito_mulher.models.ItemPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoPedidoDTO {

    private Long produtoId;
    private String titulo;
    private String cor;
    private String tamanho;
    private Integer quantidade;
    private BigDecimal preco;
    private String imagem;

    // Construtor que converte a entidade ItemPedido para DTO
    public ProdutoPedidoDTO(ItemPedido item) {
        this.produtoId = item.getProdutoId();
        this.titulo = item.getTitulo();
        this.cor = item.getCor();
        this.tamanho = item.getTamanho();
        this.quantidade = item.getQuantidade();
        this.preco = item.getPreco();
        this.imagem = item.getImagem();
    }
}