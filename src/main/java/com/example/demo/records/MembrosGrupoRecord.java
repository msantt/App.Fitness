package com.example.demo.records;

import com.example.demo.enums.Status;

import java.time.LocalDate;
import java.util.UUID;

public record MembrosGrupoRecord(UUID id, UsuariosRecord usuario, Status status, LocalDate dataEntrada, String role) {
}
