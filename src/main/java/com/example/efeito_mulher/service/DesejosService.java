package com.example.efeito_mulher.service;

import com.example.efeito_mulher.models.Produto;
import com.example.efeito_mulher.models.Usuario;
import com.example.efeito_mulher.repository.ProdutoRepository;
import com.example.efeito_mulher.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DesejosService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    public Set<Produto> listarDesejos(String email) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return usuario.getDesejos();
    }

    public void adicionarDesejo(String email, Long produtoId) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        Produto produto = produtoRepo.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        usuario.getDesejos().add(produto);
        usuarioRepo.save(usuario);
    }

    public void removerDesejo(String email, Long produtoId) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        usuario.getDesejos().removeIf(p -> p.getId().equals(produtoId));
        usuarioRepo.save(usuario);
    }
}