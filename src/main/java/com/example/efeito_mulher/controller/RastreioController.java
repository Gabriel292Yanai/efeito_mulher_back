package com.example.efeito_mulher.controller;

import com.example.efeito_mulher.dto.RastreamentoResponseDTO;
import com.example.efeito_mulher.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rastreamento")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RastreioController {

    private final PedidoRepository pedidoRepository;

    @GetMapping("/{codigo}")
    public ResponseEntity<RastreamentoResponseDTO> buscarPorCodigo(@PathVariable String codigo) {
        return pedidoRepository.findByCodigoRastreamento(codigo)
                .map(p -> new RastreamentoResponseDTO(
                        p.getCodigoRastreamento(),
                        p.getStatusRastreamento(),
                        p.getData()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}