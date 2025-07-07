package com.example.efeito_mulher.service;

import com.example.efeito_mulher.dto.*;
import com.example.efeito_mulher.models.*;
import com.example.efeito_mulher.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepo;
    private final VariacaoProdutoRepository variacaoRepo;

    /* ---------- CREATE ---------- */
    @Transactional
    public Produto criar(ProdutoRequestDTO dto) {
        // 1) salva o produto sem variações para gerar ID
        Produto salvo = produtoRepo.save(mapearProdutoBasico(new Produto(), dto));

        // 2) cria variações apontando produtoId
        List<VariacaoProduto> vers = dto.getVariacoes().stream()
                .map(v -> buildVariacao(v, salvo.getId()))
                .collect(Collectors.toList());
        variacaoRepo.saveAll(vers);

        salvo.setVariacoes(vers);
        return salvo;
    }

    /* ---------- READ ---------- */
    public List<Produto> listar()          { return produtoRepo.findAll(); }
    public Produto buscar(Long id) {
        return produtoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    /* ---------- UPDATE ---------- */
    @Transactional
    public Produto atualizar(Long id, ProdutoRequestDTO dto) {
        Produto existente = buscar(id);
        existente.getVariacoes().clear();;

        mapearProdutoBasico(existente, dto);
        produtoRepo.save(existente);

        List<VariacaoProduto> novas = dto.getVariacoes().stream()
                .map(v -> buildVariacao(v, existente.getId()))
                .collect(Collectors.toList());

        existente.getVariacoes().addAll(novas);
        return existente;
    }

    public void excluir(Long id) { produtoRepo.deleteById(id); }

    /* ---------- helpers ---------- */
    private Produto mapearProdutoBasico(Produto p, ProdutoRequestDTO dto) {
        BigDecimal base = parsePreco(dto.getPreco());
        p.setTitulo(dto.getTitulo());
        p.setCategoria(dto.getCategoria());
        p.setSubcategoria(dto.getSubcategoria());
        p.setDescricao(dto.getDescricao());
        p.setPreco(base);
        p.setPrecoPix(base.multiply(BigDecimal.valueOf(0.95)));
        p.setPrecoParcela(base.divide(BigDecimal.valueOf(6), 2, RoundingMode.HALF_UP));
        p.setImagem(dto.getImagem());
        p.setImagens(dto.getImagens());
        p.setDetalhes(dto.getDetalhes());
        return p;
    }

    private VariacaoProduto buildVariacao(VariacaoProdutoDTO v, Long produtoId) {
        return VariacaoProduto.builder()
                .cor(v.getCor())
                .tamanho(v.getTamanho())
                .quantidade(v.getQuantidade())
                .produtoId(produtoId)
                .build();
    }

    private BigDecimal parsePreco(String ptBR) {
        if (ptBR == null) return BigDecimal.ZERO;
        return new BigDecimal(ptBR.replace(".", "").replace(",", "."));
    }

}