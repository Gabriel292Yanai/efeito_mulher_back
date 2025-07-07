package com.example.efeito_mulher.service;

import com.example.efeito_mulher.dto.UsuarioRequestDTO;
import com.example.efeito_mulher.models.Usuario;
import com.example.efeito_mulher.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public Usuario cadastrar(UsuarioRequestDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        if (usuarioRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nameFirst(dto.getNameFirst())
                .nameFamily(dto.getNameFamily())
                .email(dto.getEmail())
                .senha(encoder.encode(dto.getSenha()))
                .cpf(dto.getCpf())
                .celular(dto.getCelular())
                .telefone(dto.getTelefone())
                .sexo(dto.getSexo())
                .dataNascimento(dto.getDataNascimento())
                .cep(dto.getCep())
                .endereco(dto.getEndereco())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .referencia(dto.getReferencia())
                .bairro(dto.getBairro())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .build();

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNameFirst(dto.getNameFirst());
        usuario.setNameFamily(dto.getNameFamily());
        usuario.setCelular(dto.getCelular());
        usuario.setTelefone(dto.getTelefone());
        usuario.setCep(dto.getCep());
        usuario.setEndereco(dto.getEndereco());
        usuario.setNumero(dto.getNumero());
        usuario.setComplemento(dto.getComplemento());
        usuario.setReferencia(dto.getReferencia());
        usuario.setBairro(dto.getBairro());
        usuario.setCidade(dto.getCidade());
        usuario.setEstado(dto.getEstado());

        // Atualizar senha se enviada
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuario.setSenha(encoder.encode(dto.getSenha()));
        }

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmailESenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}