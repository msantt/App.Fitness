package com.example.demo.repositories;

import com.example.demo.entities.Grupo;
import com.example.demo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    List<Grupo> findByNome(String nome);

    List<Grupo> findByStatus(Status status);

    List<Grupo> findByCriadorId(int usuario);
}
