package com.example.demo.interfaces;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Desafio;

import java.util.List;
import java.util.UUID;

public interface ICategorias {
    Categoria salvar(Categoria categoria);

    Categoria buscarPorUUID(UUID id);

    Categoria buscarPorNome(String nome);

    List<Categoria> listarTodos();

    void deletar(UUID id);

    boolean existePorNome(String nome);

    boolean existePorUUID(UUID id);

    List<Categoria> listarCategoriasComDesafiosAtivos();


    List<Desafio> listarDesafiosPorCategoriaUUID(UUID idCategoria);
}
