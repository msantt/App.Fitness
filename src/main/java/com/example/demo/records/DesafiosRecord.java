package com.example.demo.records;


import com.example.demo.entities.Patrocinador;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoDesafio;

import java.util.Date;

public record DesafiosRecord(int id, String nome, String descricao,CategoriaRecord categoria, GrupoRecord grupos, Date dataInicio, Date dataFim, Status status, String recompensa, Boolean isPublico,TipoDesafio tipoDesafio,Patrocinador patrocinador)
{
}
