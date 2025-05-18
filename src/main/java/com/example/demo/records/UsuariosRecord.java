package com.example.demo.records;

import com.example.demo.enums.Objetivo;
import com.example.demo.enums.Status;

import java.time.LocalDateTime;
import java.util.Date;

public record UsuariosRecord(int id, String nome, String email, String senha, Date dataNascimento, Objetivo objetivo, String urlFoto, LocalDateTime dataCriacao,
                             Status status, Boolean exibirHistorico) {
    public int getId() {
        return id;
    }
}
