package com.example.efeito_mulher.controller;

import com.example.efeito_mulher.dto.ProdutoRequestDTO;
import com.example.efeito_mulher.models.Produto;
import com.example.efeito_mulher.repository.ProdutoRepository;
import com.example.efeito_mulher.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoRepository repo;
    private final ProdutoService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> criarProduto(@RequestBody ProdutoRequestDTO dto) {
        Produto novo = service.criar(dto);
        return ResponseEntity.ok(novo);
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id,
                             @RequestBody ProdutoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/produtos")
    public List<Produto> listar(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String subcategoria) {

        if (categoria != null && subcategoria != null)
            return repo.findByCategoriaIgnoreCaseAndSubcategoriaIgnoreCase(categoria, subcategoria);

        if (categoria != null)
            return repo.findByCategoriaIgnoreCase(categoria);

        return repo.findAll();
    }

    public static String normalizar(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toLowerCase()
                .trim();
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProdutoRequestDTO> porCategoria(@PathVariable String categoria) {
        String cat = normalizar(categoria);
        return repo.findByCategoriaIgnoreCase(cat)
                .stream().map(ProdutoRequestDTO::new).toList();
    }

    @GetMapping("/categoria/{categoria}/{subcategoria}")
    public List<ProdutoRequestDTO> porCategoriaESub(
            @PathVariable String categoria,
            @PathVariable String subcategoria) {

        String cat = normalizar(categoria);
        String sub = normalizar(subcategoria);

        return repo.findByCategoriaIgnoreCaseAndSubcategoriaIgnoreCase(cat, sub)
                .stream().map(ProdutoRequestDTO::new).toList();
    }
}