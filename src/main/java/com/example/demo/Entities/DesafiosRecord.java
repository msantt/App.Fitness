package com.example.demo.Entities;


import java.util.Date;

public record DesafiosRecord(int id, String nome, String descricao, CategoriaRecord categoria, GrupoRecord grupos, Date dataInicio, Date dataFim, Boolean status, String recompensa, Boolean isPublico) {
}
