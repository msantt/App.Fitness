package com.example.demo.repositories;

import com.example.demo.entities.Grupo;
import com.example.demo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GrupoRepository extends JpaRepository<Grupo, UUID> {
    List<Grupo> findByNome(String nome);

    List<Grupo> findByStatus(Status status);

    List<Grupo> findByCriadorUuid(UUID usuario);

    Grupo findByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);

    boolean existsByCodigoAcesso(String codigo);
}
