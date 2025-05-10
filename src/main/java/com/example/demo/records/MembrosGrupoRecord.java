package com.example.demo.records;

import com.example.demo.enums.Status;

import java.time.LocalDate;

public record MembrosGrupoRecord(int id, UsuariosRecord usuario,Status status,LocalDate dataEntrada,String role) {
}
