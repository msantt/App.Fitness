package com.example.demo.interfaces;

import com.example.demo.entities.PagamentoDesafio;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.Usuario;

import java.util.List;

public interface IPagamentosDesafio {

    PagamentoDesafio salvar(PagamentoDesafio pagamentoDesafio);

    PagamentoDesafio buscarPorId(int id);

    List<PagamentoDesafio> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    List<PagamentoDesafio> listarPorUsuario(int idUsuario);

    List<PagamentoDesafio> listarPorDesafio(int idDesafio);
}
