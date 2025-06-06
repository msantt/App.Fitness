package com.example.demo.repositories;

import com.example.demo.entities.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {

    List<CheckIn> findByMembroDesafioUuid(UUID membrodesafioUUID);

    List<CheckIn> findByDataHoraCheckinBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query("SELECT c FROM CheckIn c WHERE c.membroDesafio.usuario.uuid = :idUsuario")
    List<CheckIn> findByUsuarioUuid(@Param("idUsuario") int idUsuario);

    boolean existsByMembroDesafio_Usuario_UuidAndMembroDesafio_Desafio_UuidAndDataHoraCheckinBetween(
            UUID usuarioUUID, UUID desafioUUID, LocalDateTime inicio, LocalDateTime fim);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    CheckIn findByUuid(UUID uuid);
}
