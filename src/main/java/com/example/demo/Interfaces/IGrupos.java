package com.example.demo.Interfaces;

import com.example.demo.Entities.Desafios;
import com.example.demo.Entities.Grupos;
import com.example.demo.Entities.Usuarios;

import java.util.List;
import java.util.Optional;

public interface IGrupos {

    Grupos salvar(Grupos grupo);

    Optional<Grupos> buscarPorId(int id);

    List<Grupos> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    List<Grupos> buscarPorStatus(boolean status);

    List<Grupos> buscarPorCriadorId(int idCriador);

    List<Grupos> buscarPorNome(String nome);
}
