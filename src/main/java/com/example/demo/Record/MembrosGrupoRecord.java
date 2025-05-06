package com.example.demo.Record;

import com.example.demo.Entities.Usuarios;
import com.example.demo.Enum.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

public record MembrosGrupoRecord(int id, UsuariosRecord usuario,Status status,LocalDate dataEntrada,String role) {
}
