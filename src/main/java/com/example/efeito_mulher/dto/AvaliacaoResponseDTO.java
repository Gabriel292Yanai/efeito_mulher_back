package com.example.efeito_mulher.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvaliacaoResponseDTO {
    private Long id;
    private Long produtoId;
    private Long usuarioId;
    private String nome;
    private Integer nota;
    private String comentario;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
}
