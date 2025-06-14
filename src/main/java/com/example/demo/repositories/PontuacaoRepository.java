package com.example.demo.repositories;

import com.example.demo.entities.Pontuacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PontuacaoRepository extends JpaRepository<Pontuacao, UUID> {
    Pontuacao findByMembroDesafioUuid(UUID membroDesafioId);

    List<Pontuacao> findByMembroDesafio_Desafio_Uuid(UUID desafioId);

    List<Pontuacao> findByMembroDesafio_Desafio_UuidOrderByPontuacaoDesc(UUID desafioId);
}
