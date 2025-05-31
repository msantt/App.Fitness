package com.example.demo.repositories;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Desafio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByNome(String nome);

    boolean existsByUuid(UUID uuid);

    @Query("SELECT c FROM Categoria c WHERE EXISTS (SELECT d FROM Desafio d WHERE d.categoria = c AND d.status = 'ATIVO')")
    List<Categoria> findCategoriasComDesafiosAtivos();
    @Query("SELECT d FROM Desafio d WHERE d.categoria.uuid = :idCategoria")
    List<Desafio> findDesafiosPorCategoriaUuid(@Param("idCategoria") UUID idCategoria);

    Categoria findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
