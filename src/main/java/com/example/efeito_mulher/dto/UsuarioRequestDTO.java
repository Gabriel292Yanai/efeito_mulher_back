package com.example.efeito_mulher.dto;

import com.example.efeito_mulher.models.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor        // Jackson precisa de construtor vazio
public class UsuarioRequestDTO {

    private Long   id;
    private String nameFirst;
    private String nameFamily;
    private String email;
    private String senha;
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

    public UsuarioRequestDTO(Usuario u) {
        this.id             = u.getId();
        this.nameFirst      = u.getNameFirst();
        this.nameFamily     = u.getNameFamily();
        this.email          = u.getEmail();
        this.senha          = u.getSenha();
        this.cpf            = u.getCpf();
        this.celular        = u.getCelular();
        this.telefone       = u.getTelefone();
        this.sexo           = u.getSexo();
        this.dataNascimento = u.getDataNascimento();
        this.cep            = u.getCep();
        this.endereco     = u.getEndereco();
        this.numero         = u.getNumero();
        this.complemento    = u.getComplemento();
        this.referencia     = u.getReferencia();
        this.bairro         = u.getBairro();
        this.cidade         = u.getCidade();
        this.estado         = u.getEstado();
    }
}