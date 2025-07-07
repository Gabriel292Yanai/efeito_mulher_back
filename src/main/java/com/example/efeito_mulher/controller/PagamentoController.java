package com.example.efeito_mulher.controller;

import com.example.efeito_mulher.dto.PagamentoRequestDTO;
import com.example.efeito_mulher.models.Usuario;
import com.example.efeito_mulher.security.CustomUserDetails;
import com.example.efeito_mulher.service.PedidoService;
import com.example.efeito_mulher.service.PagamentoService;
import com.example.efeito_mulher.dto.CartaoResponseDTO;
import com.example.efeito_mulher.dto.PixResponseDTO;
import com.example.efeito_mulher.dto.BoletoResponseDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamento")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final PedidoService pedidoService;

    @PostMapping("/pix")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PixResponseDTO> pagarPix(@AuthenticationPrincipal CustomUserDetails auth,
                                                   @RequestBody PagamentoRequestDTO dto) {
        PixResponseDTO resp = pagamentoService.gerarPix(dto.getTotal());
        pedidoService.criarPedido(auth.getUsuario(), dto.getItens(), dto.getTotal(), dto.getFrete());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/boleto")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<BoletoResponseDTO> pagarBoleto(@AuthenticationPrincipal CustomUserDetails auth,
                                                         @RequestBody PagamentoRequestDTO dto) {
        BoletoResponseDTO resp = pagamentoService.gerarBoleto(dto.getTotal());
        pedidoService.criarPedido(auth.getUsuario(), dto.getItens(), dto.getTotal(), dto.getFrete());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/cartao")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CartaoResponseDTO> pagarCartao(@AuthenticationPrincipal CustomUserDetails auth,
                                                         @RequestBody PagamentoRequestDTO dto) {
        CartaoResponseDTO resp = pagamentoService.pagarCartao(dto);
        pedidoService.criarPedido(auth.getUsuario(), dto.getItens(), dto.getTotal(), dto.getFrete());
        return ResponseEntity.ok(resp);
    }
}