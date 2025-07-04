package com.example.demo.repositories;

import com.example.demo.entities.Desafio;
import com.example.demo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DesafioRepository extends JpaRepository<Desafio, UUID> {

    List<Desafio> findByNome(String nome);

    List<Desafio> findByStatus(Status status);

    List<Desafio> findByGrupo_Uuid(UUID idGrupo);
    List<Desafio> findByCategoria_Uuid(UUID idCategoria);

    List<Desafio> findByIsPublico(Boolean isPublico);

    Desafio findByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);

    List<Desafio> findByDataFim(LocalDate doisDiasDepois);
    boolean existsByCodigo(String codigo);
    Desafio findByCodigo(String codigo);
}
