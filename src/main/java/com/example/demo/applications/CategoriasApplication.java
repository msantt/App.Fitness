package com.example.demo.applications;

import com.example.demo.config.RegraNegocioException;
import com.example.demo.entities.Categoria;
import com.example.demo.entities.Desafio;
import com.example.demo.interfaces.ICategorias;
import com.example.demo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriasApplication implements ICategorias {


    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriasApplication(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria salvar(Categoria categoria) {
        String nome = categoria.getNome();
        if (nome == null || nome.trim().isEmpty() || nome.length() < 3) {
            throw new RegraNegocioException("Nome da categoria inválido.");
        }
        if (existePorNome(categoria.getNome())) {
            throw new RegraNegocioException("Categoria com este nome já existe.");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria buscarPorId(int id) {
        return categoriaRepository.findById(id).orElseThrow(()->new RegraNegocioException(
                "Categoria não encontrada com ID: " + id
                )
        );
    }

    @Override
    public Categoria buscarPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    @Override
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll(Sort.by("nome").ascending());
    }

    @Override
    public void deletar(int id) {
        Categoria categoria = buscarPorId(id);
        List<Desafio> desafios = listarDesafiosPorCategoriaId(id);

        boolean temDesafiosAtivos = desafios.stream().anyMatch(d -> d.getStatus().equals("ATIVO"));
        if (temDesafiosAtivos) {
            throw new RegraNegocioException("Não é possível excluir categoria com desafios ativos.");
        }
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

    public List<Desafio> listarDesafiosAtivosPorCategoria(int idCategoria) {
        return listarDesafiosPorCategoriaId(idCategoria).stream()
                .filter(d -> d.getStatus().equals("ATIVO"))
                .collect(Collectors.toList());
    }

}
