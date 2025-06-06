package com.example.demo.repositories;

import com.example.demo.entities.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByUsuarioIdAndLidaFalse(UUID usuarioUuid);

    List<Notificacao> findByUsuarioUuidOrderByDataCriacaoDesc(UUID uuid);
}

