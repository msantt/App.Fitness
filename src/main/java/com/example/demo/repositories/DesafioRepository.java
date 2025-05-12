package com.example.demo.repositories;

import com.example.demo.entities.Desafio;
import com.example.demo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesafioRepository extends JpaRepository<Desafio, Integer> {

    List<Desafio> findByNome(String nome);

    List<Desafio> findByStatus(Status status);

    List<Desafio> findByGrupo_Id(int idGrupo);
    List<Desafio> findByCategoria_Id(int idCategoria);

    List<Desafio> findByIsPublico(Boolean isPublico);
}
