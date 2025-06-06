package com.example.demo.interfaces;

import com.example.demo.entities.Grupo;
import com.example.demo.enums.Status;

import java.util.List;
import java.util.UUID;

public interface IGrupos {

    Grupo salvar(Grupo grupo);

    Grupo buscarPorUUID(UUID id);

    List<Grupo> listarTodos();

    void deletar(UUID id);

    boolean existePorUUID(UUID id);

    List<Grupo> buscarPorStatus(Status status);

    List<Grupo> buscarPorCriadorUUID(UUID criador);

    List<Grupo> buscarPorNome(String nome);
}
