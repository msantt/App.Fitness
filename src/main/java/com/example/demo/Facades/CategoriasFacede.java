package com.example.demo.Facades;

import com.example.demo.Applications.CategoriasApplication;
import com.example.demo.Entities.Categoria;
import com.example.demo.Entities.Desafios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoriasFacede {
    CategoriasApplication categoriasApplication;

    @Autowired
    public CategoriasFacede(CategoriasApplication categoriasApplication) {
        this.categoriasApplication = categoriasApplication;
    }


    public Categoria salvar(Categoria categoria) {
        return categoriasApplication.salvar(categoria);
    }


    public Optional<Categoria> buscarPorId(int id) {
        return categoriasApplication.buscarPorId(id);
    }


    public Optional<Optional<Categoria>> buscarPorNome(String nome) {
        return Optional.ofNullable(categoriasApplication.buscarPorNome(nome));
    }


    public List<Categoria> listarTodos() {
        return categoriasApplication.listarTodos();
    }


    public void deletar(int id) {
        categoriasApplication.deletar(id);
    }


    public boolean existePorNome(String nome) {
        return categoriasApplication.existePorNome(nome);
    }

    public boolean existePorId(int id) {
        return categoriasApplication.existePorId(id);
    }


    public List<Categoria> listarCategoriasComDesafiosAtivos() {
        return categoriasApplication.listarCategoriasComDesafiosAtivos();
    }


    public List<Desafios> listarDesafiosPorCategoriaId(int idCategoria) {
        return categoriasApplication.listarDesafiosPorCategoriaId(idCategoria);
    }
}