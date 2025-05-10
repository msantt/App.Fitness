package com.example.demo.applications;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Desafio;
import com.example.demo.interfaces.ICategorias;
import com.example.demo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriasApplication implements ICategorias {


    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriasApplication(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria buscarPorId(int id) {
        return categoriaRepository.findById(id).orElseThrow();
    }

    @Override
    public Categoria buscarPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    @Override
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public boolean existePorNome(String nome) {
        return (categoriaRepository.findByNome(nome) != null);
    }

    @Override
    public boolean existePorId(int id) {
        return (categoriaRepository.findById(id) != null);
    }

    @Override
    public List<Categoria> listarCategoriasComDesafiosAtivos() {
        return categoriaRepository.findCategoriasComDesafiosAtivos();
    }

    @Override
    public List<Desafio> listarDesafiosPorCategoriaId(int idCategoria) {
        return categoriaRepository.findDesafiosPorCategoriaId(idCategoria);
    }
}
