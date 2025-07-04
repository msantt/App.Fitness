package com.example.demo.controllers;

import com.example.demo.entities.CheckIn;
import com.example.demo.facades.CheckinFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/check-in")
public class CheckinController {

    private final CheckinFacade checkinFacade;

    @Autowired
    public CheckinController(CheckinFacade checkinFacade) {
        this.checkinFacade = checkinFacade;
    }

    @GetMapping("/")
    public ResponseEntity<List<CheckIn>> buscarTodos() {
        List<CheckIn> checkIns = checkinFacade.listarTodos();
        return ResponseEntity.ok(checkIns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckIn> buscarPorId(@PathVariable UUID id) {
        CheckIn checkIn = checkinFacade.buscarPorId(id);
        return checkIn != null ? ResponseEntity.ok(checkIn) : ResponseEntity.notFound().build();
    }

    @GetMapping("/membro/{id}")
    public ResponseEntity<List<CheckIn>> buscarPorMembrosDesafiosId(@PathVariable UUID id) {
        List<CheckIn> checkIns = checkinFacade.buscarPorMembrosDesafiosId(id);
        return checkIns.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(checkIns);
    }

    // ✅ 1. Buscar check-ins por desafio
    @GetMapping("/desafio/{desafioId}")
    public ResponseEntity<List<CheckIn>> buscarPorDesafio(@PathVariable UUID desafioId) {
        List<CheckIn> checkIns = checkinFacade.buscarPorDesafioId(desafioId);
        return checkIns.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(checkIns);
    }

    // ✅ 2. Buscar check-ins por usuário (todos os desafios)
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CheckIn>> buscarPorUsuario(@PathVariable UUID usuarioId) {
        List<CheckIn> checkIns = checkinFacade.buscarPorUsuarioId(usuarioId);
        return checkIns.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(checkIns);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody CheckIn checkIns) {
        checkinFacade.salvar(checkIns);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckIn> atualizar(@PathVariable UUID id, @Valid @RequestBody CheckIn checkIns) {
        CheckIn existente = checkinFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        checkIns.setId(id);
        CheckIn atualizado = checkinFacade.salvar(checkIns);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        checkinFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
