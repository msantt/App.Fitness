package com.example.demo.interfaces;

import com.example.demo.entities.Pontuacao;
import java.util.List;
import java.util.UUID;

public interface IPontuacao {
    Pontuacao salvarPontuacao(Pontuacao pontuacao);

    void deletarPontuacao(UUID id);

    Pontuacao atualizarPontuacao(UUID id, Pontuacao pontuacao);

    List<Pontuacao> listarPontuacoes();

    Pontuacao buscarPontuacaoPorUUID(UUID id);

    Pontuacao buscarPontuacaoPorMembroDesafio(UUID membroDesafioId);

    List<Pontuacao> rankingPorDesafio(UUID desafioId);
}