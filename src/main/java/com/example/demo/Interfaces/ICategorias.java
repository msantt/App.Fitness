package com.example.demo.Interfaces;

import com.example.demo.Entities.Categoria;
import com.example.demo.Entities.Desafios;

import java.util.List;
import java.util.Optional;

public interface ICategorias {
    Categoria salvar(Categoria categoria);

    Optional<Categoria> buscarPorId(int id);

    Optional<Categoria> buscarPorNome(String nome);

    List<Categoria> listarTodos();

    void deletar(int id);

    boolean existePorNome(String email);

    boolean existePorId(int id);

    List<Categoria> buscarCategoriasComDesafiosAtivos();


    List<Desafios> buscarDesafiosPorCategoriaId(int idCategoria);
}
