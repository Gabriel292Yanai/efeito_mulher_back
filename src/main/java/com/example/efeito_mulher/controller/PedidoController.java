package com.example.efeito_mulher.controller;

import com.example.efeito_mulher.dto.PagamentoRequestDTO;
import com.example.efeito_mulher.dto.PedidoRequestDTO;
import com.example.efeito_mulher.dto.PedidoResponseDTO;
import com.example.efeito_mulher.security.CustomUserDetails;
import com.example.efeito_mulher.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> criarPedido(
            @AuthenticationPrincipal CustomUserDetails auth,
            @RequestBody PedidoRequestDTO dto) {
        service.criarPedido(auth.getUsuario(), dto.getItens(), dto.getTotal(), dto.getFrete());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<PedidoResponseDTO>> buscarPedidos(@AuthenticationPrincipal CustomUserDetails auth) {
        List<PedidoResponseDTO> pedidos = service.buscarPorUsuario(auth.getUsuario());
        return ResponseEntity.ok(pedidos);
    }
}