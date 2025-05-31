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
import java.util.UUID;
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
    public Categoria buscarPorUUID(UUID id) {
        return categoriaRepository.findByUuid(id);
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
    public void deletar(UUID id) {
        Categoria categoria = buscarPorUUID(id);
        List<Desafio> desafios = listarDesafiosPorCategoriaUUID(id);

        boolean temDesafiosAtivos = desafios.stream().anyMatch(d -> d.getStatus().equals("ATIVO"));
        if (temDesafiosAtivos) {
            throw new RegraNegocioException("Não é possível excluir categoria com desafios ativos.");
        }
        categoriaRepository.deleteByUuid(id);
    }

    @Override
    public boolean existePorNome(String nome) {
        return (categoriaRepository.findByNome(nome) != null);
    }

    @Override
    public boolean existePorUUID(UUID id) {
        return (categoriaRepository.findByUuid(id) != null);
    }

    @Override
    public List<Categoria> listarCategoriasComDesafiosAtivos() {
        return categoriaRepository.findCategoriasComDesafiosAtivos();
    }

    @Override
    public List<Desafio> listarDesafiosPorCategoriaUUID(UUID idCategoria) {
        return categoriaRepository.findDesafiosPorCategoriaUuid(idCategoria);
    }

    public List<Desafio> listarDesafiosAtivosPorCategoria(UUID idCategoria) {
        return listarDesafiosPorCategoriaUUID(idCategoria).stream()
                .filter(d -> d.getStatus().equals("ATIVO"))
                .collect(Collectors.toList());
    }

}
