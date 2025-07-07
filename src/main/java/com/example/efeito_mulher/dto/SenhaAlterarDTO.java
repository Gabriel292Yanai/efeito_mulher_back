package com.example.efeito_mulher.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SenhaAlterarDTO {
    private String senhaAtual;
    private String senhaNova;
}
