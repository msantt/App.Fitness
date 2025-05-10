package com.example.demo.records;


import com.example.demo.enums.Status;

import java.time.LocalDate;

public record GrupoRecord (int id, String nome, String descricao, String urlFoto, LocalDate dataCriacao,
                           Status status, UsuariosRecord criador){
}
