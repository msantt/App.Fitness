package com.example.demo.records;


import com.example.demo.entities.Patrocinador;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoDesafio;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record DesafiosRecord(UUID id, String nome, String descricao, CategoriaRecord categoria, GrupoRecord grupos, LocalDate dataInicio, LocalDate dataFim,String codigo, Status status, String valorAposta,String recompensa, Boolean isPublico, TipoDesafio tipoDesafio, Patrocinador patrocinador)
{
    public UUID getId() {
        return id;
    }
}
