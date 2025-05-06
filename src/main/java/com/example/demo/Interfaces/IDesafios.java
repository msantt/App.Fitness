package com.example.demo.Interfaces;

import com.example.demo.Entities.Desafios;
import com.example.demo.Enum.Status;

import java.util.List;
import java.util.Optional;

public interface IDesafios {

    Desafios salvar(Desafios desafio);

    Optional<Desafios> buscarPorId(int id);

    List<Desafios> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    List<Desafios> buscarPorIdGrupo(int idGrupo);

    List<Desafios> buscarPorIdCategoria(int idCategoria);

    List<Desafios> buscarPorStatus(Status status);
}
