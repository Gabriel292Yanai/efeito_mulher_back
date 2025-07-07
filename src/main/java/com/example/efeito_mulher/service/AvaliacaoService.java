package com.example.efeito_mulher.service;

import com.example.efeito_mulher.dto.AvaliacaoRequestDTO;
import com.example.efeito_mulher.dto.AvaliacaoResponseDTO;
import com.example.efeito_mulher.models.Avaliacao;
import com.example.efeito_mulher.models.Usuario;
import com.example.efeito_mulher.repository.AvaliacaoRepository;
import com.example.efeito_mulher.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public List<AvaliacaoResponseDTO> listarPorProduto(Long produtoId) {
        return repository.findByProdutoId(produtoId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AvaliacaoResponseDTO salvar(AvaliacaoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Avaliacao av = new Avaliacao();
        av.setProdutoId(dto.getProdutoId());
        av.setUsuarioId(dto.getUsuarioId());
        av.setNome(usuario.getNameFirst());
        av.setNota(dto.getNota());
        av.setComentario(dto.getComentario());
        av.setData(LocalDate.now());

        av = repository.save(av);
        return toDTO(av);
    }

    public AvaliacaoResponseDTO atualizar(Long id, AvaliacaoRequestDTO dto) {
        Avaliacao av = repository.findById(id).orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
        av.setNota(dto.getNota());
        av.setComentario(dto.getComentario());
        av = repository.save(av);
        return toDTO(av);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private AvaliacaoResponseDTO toDTO(Avaliacao av) {
        AvaliacaoResponseDTO dto = new AvaliacaoResponseDTO();
        dto.setId(av.getId());
        dto.setProdutoId(av.getProdutoId());
        dto.setUsuarioId(av.getUsuarioId());
        dto.setNome(av.getNome());
        dto.setNota(av.getNota());
        dto.setComentario(av.getComentario());
        dto.setData(av.getData());
        return dto;
    }
}