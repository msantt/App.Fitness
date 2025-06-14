package com.example.demo.interfaces;

import com.example.demo.entities.Recompensa;
import java.util.List;
import java.util.UUID;

public interface IRecompensa {
    Recompensa salvar(Recompensa recompensa);
    Recompensa buscarPorId(UUID id);
    List<Recompensa> listarTodos();
    void deletar(UUID id);
    boolean existePorId(UUID id);
    List<Recompensa> listarPorMembroDesafioId(UUID idDesafio);
}
