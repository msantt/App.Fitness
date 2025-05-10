package com.example.demo.repositories;

import com.example.demo.entities.Patrocinador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Integer> {
    Patrocinador findByNome(String nome);
}
