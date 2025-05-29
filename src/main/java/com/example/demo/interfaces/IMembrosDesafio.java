package com.example.demo.interfaces;

import com.example.demo.entities.MembrosDesafio;

import java.util.List;

public interface IMembrosDesafio {
    MembrosDesafio salvar(MembrosDesafio membroDesafio);
    List<MembrosDesafio> listarTodos();
    MembrosDesafio buscarPorId(int id);
    void deletar(int id);

    List<MembrosDesafio> buscarPorDesafioId(int desafioId);
}
