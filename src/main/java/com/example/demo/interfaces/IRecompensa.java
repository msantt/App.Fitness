package com.example.demo.interfaces;

import com.example.demo.entities.Recompensa;
import java.util.List;

public interface IRecompensa {
    Recompensa salvar(Recompensa recompensa);
    Recompensa buscarPorId(int id);
    List<Recompensa> listarTodos();
    void deletar(int id);
    boolean existePorId(int id);
    List<Recompensa> listarPorUsuarioId(int idUsuario);
    List<Recompensa> listarPorDesafioId(int idDesafio);
}
