package com.example.demo.controllers;

import com.example.demo.entities.MembrosDesafio;
import com.example.demo.enums.Status;
import com.example.demo.facades.MembrosDesafioFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/membros-desafio")
public class MembrosDesafioController {

    private final MembrosDesafioFacade facade;

    public MembrosDesafioController(MembrosDesafioFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public ResponseEntity<MembrosDesafio> criar(@RequestBody MembrosDesafio membro) {
        MembrosDesafio criado = facade.criar(membro);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping("/")
    public ResponseEntity<List<MembrosDesafio>> listar() {
        List<MembrosDesafio> lista = facade.listar();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembrosDesafio> buscarPorId(@PathVariable UUID id) {
        MembrosDesafio membro = facade.buscar(id);
        return membro != null ? ResponseEntity.ok(membro) : ResponseEntity.notFound().build();
    }

    // 1) Buscar membros por usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MembrosDesafio>> buscarPorUsuario(@PathVariable UUID usuarioId) {
        List<MembrosDesafio> membros = facade.buscarPorUsuario(usuarioId);
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }

    // 2) Buscar membros por desafio
    @GetMapping("/desafio/{desafioId}")
    public ResponseEntity<List<MembrosDesafio>> buscarPorDesafio(@PathVariable UUID desafioId) {
        List<MembrosDesafio> membros = facade.buscarPorDesafio(desafioId);
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MembrosDesafio> atualizarStatus(@PathVariable UUID id, @RequestBody Status novoStatus) {
        MembrosDesafio existente = facade.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setStatus(novoStatus);
        MembrosDesafio atualizado = facade.atualizarStatus(existente);
        return ResponseEntity.ok(atualizado);
    }


    @GetMapping("/desafio/{desafioId}/ranking")
    public ResponseEntity<List<MembrosDesafio>> rankingPorDesafio(@PathVariable UUID desafioId) {
        List<MembrosDesafio> ranking = facade.rankingPorDesafio(desafioId);
        return ranking.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(ranking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        facade.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/desistir")
    public ResponseEntity<Void> desistirDoDesafio(@PathVariable UUID id, @RequestParam UUID usuarioId) {
        boolean desistiu = facade.desistirDoDesafio(id, usuarioId);
        if (desistiu) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
