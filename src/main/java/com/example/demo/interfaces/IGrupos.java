package com.example.demo.interfaces;

import com.example.demo.entities.Grupo;
import com.example.demo.enums.Status;

import java.util.List;
import java.util.Optional;

public interface IGrupos {

    Grupo salvar(Grupo grupo);

    Grupo buscarPorId(int id);

    List<Grupo> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    List<Grupo> buscarPorStatus(Status status);

    List<Grupo> buscarPorCriadorId(int criador);

    List<Grupo> buscarPorNome(String nome);
}
