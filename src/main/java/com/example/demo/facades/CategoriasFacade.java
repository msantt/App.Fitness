package com.example.demo.facades;

import com.example.demo.applications.CategoriasApplication;
import com.example.demo.entities.Categoria;
import com.example.demo.entities.Desafio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoriasFacade {
    CategoriasApplication categoriasApplication;

    @Autowired
    public CategoriasFacade(CategoriasApplication categoriasApplication) {
        this.categoriasApplication = categoriasApplication;
    }


    public Categoria salvar(Categoria categoria) {
        return categoriasApplication.salvar(categoria);
    }


    public Categoria buscarPorId(int id) {
        return categoriasApplication.buscarPorId(id);
    }


    public Categoria buscarPorNome(String nome) {
        return categoriasApplication.buscarPorNome(nome);
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


    public List<Desafio> listarDesafiosPorCategoriaId(int idCategoria) {
        return categoriasApplication.listarDesafiosPorCategoriaId(idCategoria);
    }

    public List<Desafio> listarDesafiosAtivosPorCategoria(int idCategoria) {
        return categoriasApplication.listarDesafiosAtivosPorCategoria(idCategoria);
    }

}