package com.example.demo.Repositories;

import com.example.demo.Entities.Grupos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GruposRepository extends JpaRepository<Grupos, Integer> {
    List<Grupos> findByNome(String nome);

    List<Grupos> findByStatus(Boolean status);

    List<Grupos> findByCriadorId(int usuario);
}
