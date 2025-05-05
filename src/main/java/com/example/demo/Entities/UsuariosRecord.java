package com.example.demo.Entities;

import java.util.Date;

public record UsuariosRecord(int id,String nome,String email,String senha,Date dataNascimento,String objetivo,String urlFoto, Date dataCriacao,Boolean status,Boolean exibirHistorico) {
}
