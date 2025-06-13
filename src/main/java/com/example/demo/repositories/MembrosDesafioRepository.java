package com.example.demo.repositories;

import com.example.demo.entities.MembrosDesafio;
import com.example.demo.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MembrosDesafioRepository extends JpaRepository<MembrosDesafio, Integer> {

    boolean existsByUsuarioUuidAndDesafioUuid(UUID usuarioUUID, UUID desafioUUID);

    List<MembrosDesafio> findByDesafioUuid(UUID desafioUUID);

    boolean existsByUuid(UUID uuid);
    MembrosDesafio findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);

    boolean existsByDesafioIdAndUsuarioIdAndRole(UUID desafioId, UUID usuarioId, TipoUsuario role);
}
