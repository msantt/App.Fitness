package com.example.demo.interfaces;

import com.example.demo.entities.Carteira;

import java.util.List;

public interface ICarteiras {
    Carteira salvar(Carteira carteira);

    Carteira buscarPorId(int id);

    Carteira buscarPorUsuarioId(int idUsuario);

    List<Carteira> listarTodos();

    void deletar(int id);

    boolean existePorId(int id);

    boolean existePorUsuarioId(int idUsuario);
}
