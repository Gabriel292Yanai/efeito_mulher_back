package com.example.efeito_mulher.controller;

import com.example.efeito_mulher.dto.SenhaAlterarDTO;
import com.example.efeito_mulher.dto.UsuarioAtualizarDTO;
import com.example.efeito_mulher.dto.UsuarioRequestDTO;
import com.example.efeito_mulher.models.Usuario;
import com.example.efeito_mulher.security.CustomUserDetails;
import com.example.efeito_mulher.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // permite requisições do front
public class UsuarioController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioRequestDTO dto) {
        try {
            Usuario novoUsuario = usuarioService.cadastrar(dto);
            return ResponseEntity.ok(novoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarPorEmailSenha(
            @RequestParam String email,
            @RequestParam String senha
    ) {
        Optional<Usuario> usuario = usuarioService.buscarPorEmailESenha(email, senha);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUsuarioLogado(@AuthenticationPrincipal CustomUserDetails details) {
        Usuario usuario = details.getUsuario();
        return ResponseEntity.ok(new UsuarioRequestDTO(usuario));
    }


    @PutMapping("/me")
    public ResponseEntity<?> atualizarUsuario(
            @AuthenticationPrincipal CustomUserDetails details,
            @RequestBody UsuarioAtualizarDTO dados
    ) {
        Usuario usuario = details.getUsuario();

        usuario.setTelefone(dados.getTelefone());
        usuario.setCelular(dados.getCelular());
        usuario.setCep(dados.getCep());
        usuario.setEndereco(dados.getEndereco());
        usuario.setNumero(dados.getNumero());
        usuario.setComplemento(dados.getComplemento());
        usuario.setBairro(dados.getBairro());
        usuario.setCidade(dados.getCidade());
        usuario.setEstado(dados.getEstado());

        usuarioService.salvar(usuario);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/alterar-senha")
    public ResponseEntity<?> alterarSenha(
            @AuthenticationPrincipal CustomUserDetails details,
            @RequestBody SenhaAlterarDTO dto
    ) {
        Usuario usuario = details.getUsuario();

        if (!encoder.matches(dto.getSenhaAtual(), usuario.getSenha())) {
            return ResponseEntity.badRequest().body("Senha atual incorreta");
        }
        usuario.setSenha(encoder.encode(dto.getSenhaNova()));
        usuarioService.salvar(usuario);
        return ResponseEntity.ok("Senha alterada com sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    // Atualizar dados do usuário
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto) {
        try {
            Usuario atualizado = usuarioService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}