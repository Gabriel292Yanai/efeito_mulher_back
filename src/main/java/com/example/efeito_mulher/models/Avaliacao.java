package com.example.efeito_mulher.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Avaliacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produtoId;
    private Long usuarioId;
    private String nome;      // nome do autor
    private Integer nota;     // 1-5
    @Column(length = 1000)
    private String comentario;
    private LocalDate data;      // ISO-8601 simples (yyyy-MM-ddTHH:mm:ss)
}