package com.example.demo.records;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;

import java.util.UUID;

public record MembroDesafioRecord(UUID id, Usuario usuario, Desafio desafio, Status status) {
}
