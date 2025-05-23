package com.example.demo.repositories;

import com.example.demo.entities.MembrosDesafio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembrosDesafioRepository extends JpaRepository<MembrosDesafio, Integer> {

    boolean existsByUsuarioIdAndDesafioId(int usuarioId, int desafioId);
}
