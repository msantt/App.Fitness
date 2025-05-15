package com.example.demo.repositories;

import com.example.demo.entities.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {

    List<CheckIn> findByMembroDesafioId(int membrodesafioId);

    List<CheckIn> findByDataHoraCheckinBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query("SELECT c FROM CheckIn c WHERE c.membroDesafio.usuario.id = :idUsuario")
    List<CheckIn> findByUsuarioId(@Param("idUsuario") int idUsuario);

    boolean existsByMembroDesafio_Usuario_IdAndMembroDesafio_Desafio_IdAndDataHoraCheckinBetween(
            int usuarioId, int desafioId, LocalDateTime inicio, LocalDateTime fim);
}
