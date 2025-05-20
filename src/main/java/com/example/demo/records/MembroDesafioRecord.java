package com.example.demo.records;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.Usuario;
import com.example.demo.enums.Status;

public record MembroDesafioRecord(int id, Usuario usuario, Desafio desafio, Status status) {
}
