package com.example.demo.Record;

import com.example.demo.Enum.Objetivo;
import com.example.demo.Enum.Status;

import java.util.Date;

public record UsuariosRecord(int id, String nome, String email, String senha, Date dataNascimento, Objetivo objetivo, String urlFoto, Date dataCriacao,
                             Status status, Boolean exibirHistorico) {
}
