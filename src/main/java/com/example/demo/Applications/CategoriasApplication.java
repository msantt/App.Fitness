package com.example.demo.Applications;

import com.example.demo.Entities.Categoria;
import com.example.demo.Entities.Desafios;
import com.example.demo.Interfaces.ICategorias;
import com.example.demo.Repositories.CategoriaRepository;
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
    public Optional<Categoria> buscarPorId(int id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Optional<Categoria> buscarPorNome(String nome) {
        return Optional.ofNullable(categoriaRepository.findByNome(nome));
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
    public List<Desafios> listarDesafiosPorCategoriaId(int idCategoria) {
        return categoriaRepository.findDesafiosPorCategoriaId(idCategoria);
    }
}
