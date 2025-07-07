package com.example.efeito_mulher.dto;

import com.example.efeito_mulher.models.StatusRastreamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RastreamentoResponseDTO {
    private String codigoRastreamento;
    private StatusRastreamento statusRastreamento;
    private LocalDateTime dataPedido;
}