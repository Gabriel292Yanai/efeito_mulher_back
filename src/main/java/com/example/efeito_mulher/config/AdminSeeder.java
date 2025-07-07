package com.example.efeito_mulher.config;

import com.example.efeito_mulher.models.Usuario;
import com.example.efeito_mulher.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.PasswordAuthentication;
import java.util.List;

@Configuration @RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String...args) {
        repo.findByEmail("admin@admin.com").orElseGet(() -> {
            Usuario admin = Usuario.builder()
                    .nameFirst("Admin").nameFamily("Root")
                    .email("admin@admin.com")
                    .senha(encoder.encode("admin12345"))
                    .cpf("00000000000")
                    .roles(List.of("ROLE_ADMIN"))
                    .build();
            return repo.save(admin);
        });
    }
}
