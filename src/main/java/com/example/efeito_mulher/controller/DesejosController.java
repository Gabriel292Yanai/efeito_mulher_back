package com.example.efeito_mulher.controller;

import com.example.efeito_mulher.models.Produto;
import com.example.efeito_mulher.service.DesejosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/desejos")
public class DesejosController {

    @Autowired
    private DesejosService desejosService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public Set<Produto> listarDesejos(Principal principal) {
        String email = principal.getName(); // Pega o e-mail do usuário autenticado
        return desejosService.listarDesejos(email);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/{produtoId}")
    public ResponseEntity<?> adicionar(@PathVariable Long produtoId, Principal principal) {
        String email = principal.getName();
        desejosService.adicionarDesejo(email, produtoId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/{produtoId}")
    public ResponseEntity<?> remover(@PathVariable Long produtoId, Principal principal) {
        String email = principal.getName();
        desejosService.removerDesejo(email, produtoId);
        return ResponseEntity.noContent().build();
    }
}