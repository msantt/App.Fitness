package com.example.demo.repositories;

import com.example.demo.entities.Convite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConviteRepository extends JpaRepository<Convite, UUID> {
    List<Convite> findByGrupoOuDesafioIdAndConvidado_Id(UUID grupoOuDesafioId, UUID convidadoId);
}