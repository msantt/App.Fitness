package com.example.demo.interfaces;

import com.example.demo.entities.PagamentoDesafio;

import java.util.List;
import java.util.UUID;

public interface IPagamentosDesafio {

    PagamentoDesafio salvar(PagamentoDesafio pagamentoDesafio);

    PagamentoDesafio buscarPorUuid(UUID id);

    List<PagamentoDesafio> listarTodos();

    void deletar(UUID id);

    boolean existePorUuid(UUID id);

    List<PagamentoDesafio> listarPorUsuario(UUID idUsuario);

    List<PagamentoDesafio> listarPorDesafio(UUID idDesafio);
}
