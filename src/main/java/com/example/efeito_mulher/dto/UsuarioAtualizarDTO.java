package com.example.efeito_mulher.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioAtualizarDTO {

    /* Contato */
    private String celular;
    private String telefone;

    /* Endereço */
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String referencia;
    private String bairro;
    private String cidade;
    private String estado;
}
