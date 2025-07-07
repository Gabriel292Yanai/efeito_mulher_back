package com.example.efeito_mulher.controller;

import com.example.efeito_mulher.dto.AvaliacaoRequestDTO;
import com.example.efeito_mulher.dto.AvaliacaoResponseDTO;
import com.example.efeito_mulher.service.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    private final AvaliacaoService service;

    @GetMapping("/produto/{produtoId}")
    public List<AvaliacaoResponseDTO> listarPorProduto(@PathVariable Long produtoId) {
        return service.listarPorProduto(produtoId);
    }

    @PostMapping
    public AvaliacaoResponseDTO salvar(@RequestBody AvaliacaoRequestDTO dto) {
        return service.salvar(dto);
    }

    @PutMapping("/{id}")
    public AvaliacaoResponseDTO atualizar(@PathVariable Long id, @RequestBody AvaliacaoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}