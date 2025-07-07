package com.example.efeito_mulher.controller;

import com.example.efeito_mulher.models.Usuario;
import com.example.efeito_mulher.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/admin") @RequiredArgsConstructor
public class AdminController {

    private final UsuarioRepository repo;

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> listarUsuarios() {
        return repo.findAll();
    }
}
