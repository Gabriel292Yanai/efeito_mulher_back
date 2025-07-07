package com.example.efeito_mulher.dto;

import com.example.efeito_mulher.models.Produto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProdutoRequestDTO {
    private Long id;
    private String titulo;
    private String categoria;
    private String subcategoria;
    private String descricao;
    private String preco;      // "299,00" (pt-BR)
    private String imagem;
    private List<String> imagens;
    private List<String> detalhes;
    private List<VariacaoProdutoDTO> variacoes;

    public ProdutoRequestDTO(Produto produto) {
        this.id = produto.getId();
        this.titulo = produto.getTitulo();
        this.categoria = produto.getCategoria();
        this.subcategoria = produto.getSubcategoria();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco() != null ? produto.getPreco().toString() : null;
        this.imagem = produto.getImagem();
        this.imagens = produto.getImagens();
        this.detalhes = produto.getDetalhes();
        this.variacoes = produto.getVariacoes() != null
                ? produto.getVariacoes().stream()
                .map(VariacaoProdutoDTO::new)
                .collect(Collectors.toList())
                : null;
    }
}