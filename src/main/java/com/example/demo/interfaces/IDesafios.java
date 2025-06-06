package com.example.demo.interfaces;

import com.example.demo.entities.Desafio;
import com.example.demo.enums.Status;

import java.util.List;
import java.util.UUID;

public interface IDesafios {

    Desafio salvar(Desafio desafio);

    Desafio buscarPorUUID(UUID id);

    List<Desafio> listarTodos();

    void deletar(UUID id);

    boolean existePorUUID(UUID id);

    List<Desafio> buscarPorUUIDGrupo(UUID idGrupo);

    List<Desafio> buscarPorUUIDCategoria(UUID idCategoria);

    List<Desafio> buscarPorStatus(Status status);
}
