package com.example.demo.Record;


import com.example.demo.Entities.Patrocinador;
import com.example.demo.Enum.Status;
import com.example.demo.Enum.TipoDesafio;
import jakarta.persistence.*;

import java.util.Date;

public record DesafiosRecord(int id, String nome, String descricao,CategoriaRecord categoria, GrupoRecord grupos, Date dataInicio, Date dataFim, Status status, String recompensa, Boolean isPublico,TipoDesafio tipoDesafio,Patrocinador patrocinador)
{
}
