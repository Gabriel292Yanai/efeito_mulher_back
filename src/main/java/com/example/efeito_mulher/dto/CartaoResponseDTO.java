package com.example.efeito_mulher.dto;

public record CartaoResponseDTO(String status,
                                String autorizacao,
                                String nsu,
                                String bandeira,
                                String mensagem) { }
