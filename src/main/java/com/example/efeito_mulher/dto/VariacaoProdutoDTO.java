package com.example.efeito_mulher.dto;

import com.example.efeito_mulher.models.VariacaoProduto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VariacaoProdutoDTO {
    private String cor;
    private String tamanho;
    private Integer quantidade;

    public VariacaoProdutoDTO(VariacaoProduto variacao) {
        this.cor = variacao.getCor();
        this.tamanho = variacao.getTamanho();
        this.quantidade = variacao.getQuantidade();
    }
}
