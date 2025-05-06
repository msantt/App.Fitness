package com.example.demo.Record;


import com.example.demo.Entities.MembrosGrupo;
import com.example.demo.Enum.Status;

import java.time.LocalDate;
import java.util.List;

public record GrupoRecord (int id, String nome, String descricao, String urlFoto, LocalDate dataCriacao,
                           Status status, UsuariosRecord criador){
}
