package com.example.demo.controllers;

import com.example.demo.entities.Pontuacao;
import com.example.demo.facades.PontuacaoFacede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Pontuacao salvarPontuacao(@RequestBody Pontuacao pontuacao) {
        return pontuacaoFacede.salvarPontuacao(pontuacao);
    }

    @DeleteMapping("/{id}")
    public void deletarPontuacao(@PathVariable UUID id) {
        pontuacaoFacede.deletarPontuacao(id);
    }

    @PutMapping("/{id}")
    public Pontuacao atualizarPontuacao(@PathVariable UUID id, @RequestBody Pontuacao pontuacao) {
        return pontuacaoFacede.atualizarPontuacao(id, pontuacao);
    }

    @GetMapping
    public List<Pontuacao> listarPontuacoes() {
        return pontuacaoFacede.listarPontuacoes();
    }

    @GetMapping("/{id}")
    public Pontuacao buscarPontuacaoPorUUID(@PathVariable UUID id) {
        return pontuacaoFacede.buscarPontuacaoPorUUID(id);
    }

    @GetMapping("/membro-desafio/{membroDesafioId}")
    public Pontuacao buscarPontuacaoPorMembroDesafio(@PathVariable UUID membroDesafioId) {
        return pontuacaoFacede.buscarPontuacaoPorMembroDesafio(membroDesafioId);
    }

    @GetMapping("/ranking/{desafioId}")
    public List<Pontuacao> rankingPorDesafio(@PathVariable UUID desafioId) {
        return pontuacaoFacede.rankingPorDesafio(desafioId);
    }
}