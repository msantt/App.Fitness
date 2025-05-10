package com.example.demo.interfaces;

import com.example.demo.entities.Desafio;
import com.example.demo.enums.Status;

import java.util.List;
import java.util.Optional;

public interface IDesafios {

    Desafio salvar(Desafio desafio);

    Desafio buscarPorId(int id);

    List<Desafio> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    List<Desafio> buscarPorIdGrupo(int idGrupo);

    List<Desafio> buscarPorIdCategoria(int idCategoria);

    List<Desafio> buscarPorStatus(Status status);
}
