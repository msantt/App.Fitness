package com.example.demo.Entities;


import java.time.LocalDate;

public record GrupoRecord (int id,String nome,String descricao,String urlFoto,LocalDate dataCriacao,Boolean status, UsuariosRecord criador){
}
