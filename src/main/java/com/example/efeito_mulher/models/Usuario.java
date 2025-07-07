package com.example.efeito_mulher.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameFirst;
    private String nameFamily;
    private String email;
    private String senha;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String celular;
    private String telefone;
    private String sexo;
    private String dataNascimento;

    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String referencia;
    private String bairro;
    private String cidade;
    private String estado;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = List.of("ROLE_USER");

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_desejos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private Set<Produto> desejos = new HashSet<>();
}