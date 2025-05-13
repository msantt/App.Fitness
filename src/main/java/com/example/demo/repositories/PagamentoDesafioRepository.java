package com.example.demo.repositories;

import com.example.demo.entities.PagamentoDesafio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagamentoDesafioRepository extends JpaRepository<PagamentoDesafio, Integer> {

    boolean existsById(int id);

    @Query("SELECT p FROM PagamentoDesafio p WHERE p.usuario.id = :idUsuario")
    List<PagamentoDesafio> findByUsuarioId(@Param("idUsuario") int idUsuario);

    @Query("SELECT p FROM PagamentoDesafio p WHERE p.desafio.id = :idDesafio")
    List<PagamentoDesafio> findByDesafioId(@Param("idDesafio") int idDesafio);
}
