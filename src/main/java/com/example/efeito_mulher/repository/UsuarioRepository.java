package com.example.efeito_mulher.repository;

import com.example.efeito_mulher.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}