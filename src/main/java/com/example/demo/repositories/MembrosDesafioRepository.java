package com.example.demo.repositories;

import com.example.demo.entities.MembrosDesafio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembrosDesafioRepository extends JpaRepository<MembrosDesafio, Integer> {

    boolean existsByUsuarioIdAndDesafioId(int usuarioId, int desafioId);

    List<MembrosDesafio> findByDesafioId(int desafioId);
}
