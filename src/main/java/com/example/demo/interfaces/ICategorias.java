package com.example.demo.interfaces;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Desafio;

import java.util.List;
import java.util.Optional;

public interface ICategorias {
    Categoria salvar(Categoria categoria);

    Categoria buscarPorId(int id);

    Categoria buscarPorNome(String nome);

    List<Categoria> listarTodos();

    void deletar(int id);

    boolean existePorNome(String nome);

    boolean existePorId(int id);

    List<Categoria> listarCategoriasComDesafiosAtivos();


    List<Desafio> listarDesafiosPorCategoriaId(int idCategoria);
}
