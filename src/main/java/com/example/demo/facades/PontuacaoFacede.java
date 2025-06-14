package com.example.demo.facades;

import com.example.demo.entities.Pontuacao;
import com.example.demo.interfaces.IPontuacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PontuacaoFacede {

    private final IPontuacao pontuacaoApplication;

    @Autowired
    public PontuacaoFacede(IPontuacao pontuacaoApplication) {
        this.pontuacaoApplication = pontuacaoApplication;
    }

    public Pontuacao salvarPontuacao(Pontuacao pontuacao) {
        return pontuacaoApplication.salvarPontuacao(pontuacao);
    }

    public void deletarPontuacao(UUID id) {
        pontuacaoApplication.deletarPontuacao(id);
    }

    public Pontuacao atualizarPontuacao(UUID id, Pontuacao pontuacao) {
        return pontuacaoApplication.atualizarPontuacao(id, pontuacao);
    }

    public List<Pontuacao> listarPontuacoes() {
        return pontuacaoApplication.listarPontuacoes();
    }

    public Pontuacao buscarPontuacaoPorUUID(UUID id) {
        return pontuacaoApplication.buscarPontuacaoPorUUID(id);
    }

    public Pontuacao buscarPontuacaoPorMembroDesafio(UUID membroDesafioId) {
        return pontuacaoApplication.buscarPontuacaoPorMembroDesafio(membroDesafioId);
    }

    public List<Pontuacao> rankingPorDesafio(UUID desafioId) {
        return pontuacaoApplication.rankingPorDesafio(desafioId);
    }
}