package com.example.demo.repositories;

import com.example.demo.entities.PagamentoDesafio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PagamentoDesafioRepository extends JpaRepository<PagamentoDesafio, Integer> {

    boolean existsByUuid(UUID id);

    @Query("SELECT p FROM PagamentoDesafio p WHERE p.usuario.uuid = :idUsuario")
    List<PagamentoDesafio> findByUsuarioUuid(@Param("idUsuario") UUID idUsuario);

    @Query("SELECT p FROM PagamentoDesafio p WHERE p.desafio.uuid = :idDesafio")
    List<PagamentoDesafio> findByDesafioUuid(@Param("idDesafio") UUID idDesafio);

    void deleteByUuid(UUID id);

    PagamentoDesafio findByUuid(UUID id);
}
