package com.example.demo.Interfaces;

import com.example.demo.Entities.Grupos;

import java.util.List;
import java.util.Optional;

public interface IGrupos {

    Grupos salvar(Grupos grupo);

    Optional<Grupos> buscarPorId(int id);

    List<Grupos> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    List<Grupos> buscarPorStatus(boolean status);

    List<Grupos> buscarPorCriadorId(int criador);

    List<Grupos> buscarPorNome(String nome);
}
