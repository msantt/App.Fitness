package com.example.demo.controllers;

import com.example.demo.entities.Pontuacao;
import com.example.demo.facades.PontuacaoFacede;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pontuacoes")
public class PontuacaoController {

    private final PontuacaoFacede pontuacaoFacede;

    @Autowired
    public PontuacaoController(PontuacaoFacede pontuacaoFacede) {
        this.pontuacaoFacede = pontuacaoFacede;
    }

    @PostMapping
    public ResponseEntity<Pontuacao> salvarPontuacao(@Valid @RequestBody Pontuacao pontuacao) {
        Pontuacao salva = pontuacaoFacede.salvarPontuacao(pontuacao);
        return ResponseEntity
                .created(URI.create("/pontuacoes/" + salva.getId()))
                .body(salva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPontuacao(@PathVariable UUID id) {
        pontuacaoFacede.deletarPontuacao(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pontuacao> atualizarPontuacao(@PathVariable UUID id, @Valid @RequestBody Pontuacao pontuacao) {
        Pontuacao existente = pontuacaoFacede.buscarPontuacaoPorUUID(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        pontuacao.setId(id);
        Pontuacao atualizada = pontuacaoFacede.atualizarPontuacao(id, pontuacao);
        return ResponseEntity.ok(atualizada);
    }

    @GetMapping
    public ResponseEntity<List<Pontuacao>> listarPontuacoes() {
        List<Pontuacao> pontuacoes = pontuacaoFacede.listarPontuacoes();
        if (pontuacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pontuacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pontuacao> buscarPontuacaoPorUUID(@PathVariable UUID id) {
        Pontuacao pontuacao = pontuacaoFacede.buscarPontuacaoPorUUID(id);
        if (pontuacao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pontuacao);
    }

    @GetMapping("/membro-desafio/{membroDesafioId}")
    public ResponseEntity<Pontuacao> buscarPontuacaoPorMembroDesafio(@PathVariable UUID membroDesafioId) {
        Pontuacao pontuacao = pontuacaoFacede.buscarPontuacaoPorMembroDesafio(membroDesafioId);
        if (pontuacao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pontuacao);
    }

    @GetMapping("/ranking/{desafioId}")
    public ResponseEntity<List<Pontuacao>> rankingPorDesafio(@PathVariable UUID desafioId) {
        List<Pontuacao> ranking = pontuacaoFacede.rankingPorDesafio(desafioId);
        if (ranking.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ranking);
    }
}
