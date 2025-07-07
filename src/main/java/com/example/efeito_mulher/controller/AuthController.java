package com.example.efeito_mulher.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.example.efeito_mulher.dto.LoginRequestDTO;
import com.example.efeito_mulher.dto.LoginResponseDTO;
import com.example.efeito_mulher.security.JwtUtil;
import com.example.efeito_mulher.models.Usuario;
import com.example.efeito_mulher.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository repo;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
        );

        UserDetails ud = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.gerarToken(ud);
        Usuario usuario = repo.findByEmail(dto.getEmail()).get();

        return ResponseEntity.ok(new LoginResponseDTO(token, usuario));
    }
}
