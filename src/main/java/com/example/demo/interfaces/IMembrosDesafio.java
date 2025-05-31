package com.example.demo.interfaces;

import com.example.demo.entities.MembrosDesafio;

import java.util.List;
import java.util.UUID;

public interface IMembrosDesafio {
    MembrosDesafio salvar(MembrosDesafio membroDesafio);
    List<MembrosDesafio> listarTodos();
    MembrosDesafio buscarPorUUID(UUID id);
    void deletar(UUID id);

    List<MembrosDesafio> buscarPorDesafioUUID(UUID desafioUUID);
}
