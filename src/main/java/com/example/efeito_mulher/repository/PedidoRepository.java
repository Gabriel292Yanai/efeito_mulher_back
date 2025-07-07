package com.example.efeito_mulher.repository;

import com.example.efeito_mulher.models.Pedido;
import com.example.efeito_mulher.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuario(Usuario usuario);
    Optional<Pedido> findByCodigoRastreamento(String codigoRastreamento);
}