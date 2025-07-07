package com.example.efeito_mulher.service;

import com.example.efeito_mulher.dto.ItemCarrinhoDTO;
import com.example.efeito_mulher.dto.PedidoResponseDTO;
import com.example.efeito_mulher.models.ItemPedido;
import com.example.efeito_mulher.models.Pedido;
import com.example.efeito_mulher.models.StatusRastreamento;
import com.example.efeito_mulher.models.Usuario;
import com.example.efeito_mulher.repository.ItemPedidoRepository;
import com.example.efeito_mulher.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public void criarPedido(Usuario usuario, List<ItemCarrinhoDTO> itens, BigDecimal total, BigDecimal frete) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        // Cria o pedido principal
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setData(LocalDateTime.now());
        pedido.setCodigoRastreamento(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        pedido.setStatusRastreamento(StatusRastreamento.REALIZADO);
        pedido.setFrete(frete);
        pedido.setTotal(total);
        pedido = pedidoRepository.save(pedido); // salva e recupera o ID

        // Cria os itens do pedido
        for (ItemCarrinhoDTO dto : itens) {
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProdutoId(dto.getId());
            item.setTitulo(dto.getTitulo());
            item.setQuantidade(dto.getQuantidade());
            item.setPreco(new BigDecimal(dto.getPreco().toString())); // ou adapte conforme necessário
            item.setCor(dto.getCor());
            item.setTamanho(dto.getTamanho());
            item.setImagem(dto.getImagem());

            itemPedidoRepository.save(item);
        }
    }

    public List<PedidoResponseDTO> buscarPorUsuario(Usuario usuario) {
        // Busca todos os pedidos ligados ao usuário
        return pedidoRepository.findByUsuario(usuario).stream()
                .map(PedidoResponseDTO::new)     // converte entidade → DTO
                .collect(Collectors.toList());
    }

}