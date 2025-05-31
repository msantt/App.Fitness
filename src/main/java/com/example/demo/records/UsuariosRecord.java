package com.example.demo.records;

import com.example.demo.enums.Objetivo;
import com.example.demo.enums.Status;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record UsuariosRecord(UUID id, String nome, String email, String senha, Date dataNascimento, Objetivo objetivo, String urlFoto, LocalDateTime dataCriacao,
                             Status status, Boolean exibirHistorico) {
    public UUID getId() {
        return id;
    }
}
