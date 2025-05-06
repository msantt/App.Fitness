package com.example.demo.Repositories;

import com.example.demo.Entities.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Integer> {
    Optional<Patrocinador> findByNome(String nome);
}
