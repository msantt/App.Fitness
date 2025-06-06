package com.example.demo.facades;

import com.example.demo.applications.CategoriasApplication;
import com.example.demo.entities.Categoria;
import com.example.demo.entities.Desafio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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


    public Categoria buscarPorId(UUID id) {
        return categoriasApplication.buscarPorUUID(id);
    }


    public Categoria buscarPorNome(String nome) {
        return categoriasApplication.buscarPorNome(nome);
    }


    public List<Categoria> listarTodos() {
        return categoriasApplication.listarTodos();
    }


    public void deletar(UUID id) {
        categoriasApplication.deletar(id);
    }


    public boolean existePorNome(String nome) {
        return categoriasApplication.existePorNome(nome);
    }

    public boolean existePorId(UUID id) {
        return categoriasApplication.existePorUUID(id);
    }


    public List<Categoria> listarCategoriasComDesafiosAtivos() {
        return categoriasApplication.listarCategoriasComDesafiosAtivos();
    }


    public List<Desafio> listarDesafiosPorCategoriaUUID(UUID idCategoria) {
        return categoriasApplication.listarDesafiosPorCategoriaUUID(idCategoria);
    }

    public List<Desafio> listarDesafiosAtivosPorCategoria(UUID idCategoria) {
        return categoriasApplication.listarDesafiosAtivosPorCategoria(idCategoria);
    }

}