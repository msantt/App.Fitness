package com.example.demo.Repositories;

import com.example.demo.Entities.Categoria;
import com.example.demo.Entities.Desafios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByNome(String nome);

    boolean existsById(int id);

    @Query("SELECT c FROM Categoria c WHERE EXISTS (SELECT d FROM Desafios d WHERE d.categoria = c AND d.status = 'ATIVO')")
    List<Categoria> findCategoriasComDesafiosAtivos();
    @Query("SELECT d FROM Desafios d WHERE d.categoria.id = :idCategoria")
    List<Desafios> findDesafiosPorCategoriaId(@Param("idCategoria") int idCategoria);
}
