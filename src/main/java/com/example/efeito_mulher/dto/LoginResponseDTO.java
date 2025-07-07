package com.example.efeito_mulher.dto;

import com.example.efeito_mulher.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private Usuario usuario;
}
