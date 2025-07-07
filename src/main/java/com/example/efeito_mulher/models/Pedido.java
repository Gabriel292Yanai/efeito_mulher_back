package com.example.efeito_mulher.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    private LocalDateTime data;
    private BigDecimal total;
    private BigDecimal frete;

    @Column(unique = true)
    private String codigoRastreamento;

    @Enumerated(EnumType.STRING)
    private StatusRastreamento statusRastreamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(nullable = false)
    private String status = "PENDENTE"; // ou outro valor padrão

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}