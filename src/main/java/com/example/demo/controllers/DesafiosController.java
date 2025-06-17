package com.example.demo.controllers;

import com.example.demo.entities.Desafio;
import com.example.demo.facades.DesafiosFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/desafios")
public class DesafiosController {
    private final DesafiosFacade desafiosFacade;

    @Autowired
    public DesafiosController(DesafiosFacade desafiosFacade) {
        this.desafiosFacade = desafiosFacade;
    }

    @GetMapping("/")
    public ResponseEntity<List<Desafio>> buscarTodos() {
        List<Desafio> desafios = desafiosFacade.listarTodos();
        return ResponseEntity.ok(desafios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Desafio> buscarPorId(@PathVariable UUID id) {
        Desafio desafio = desafiosFacade.buscarPorId(id);
        return desafio != null ? ResponseEntity.ok(desafio) : ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Desafio>> buscarPorCategoria(@PathVariable UUID id) {
        List<Desafio> desafios = desafiosFacade.buscarPorIdCategoria(id);
        return desafios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(desafios);
    }

    @GetMapping("/por-grupo/{id}") // rota renomeada para evitar conflito
    public ResponseEntity<List<Desafio>> buscarPorGrupo(@PathVariable UUID id) {
        List<Desafio> desafios = desafiosFacade.buscarPorIdGrupo(id);
        return desafios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(desafios);
    }

    @PostMapping
    public ResponseEntity<Desafio> salvar(@Valid @RequestBody Desafio desafio) {
        Desafio salvo = desafiosFacade.salvar(desafio);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Desafio> atualizar(@PathVariable UUID id, @Valid @RequestBody Desafio desafio) {
        Desafio existente = desafiosFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        desafio.setId(id);
        Desafio atualizado = desafiosFacade.salvar(desafio);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        Desafio existente = desafiosFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        desafiosFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Desafio> buscarPorCodigo(@PathVariable String codigo) {
        Desafio desafio = desafiosFacade.buscarPorCodigo(codigo);
        return Optional.ofNullable(desafio)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarDesafio(@PathVariable UUID id, @RequestParam UUID usuarioId) {
        boolean cancelado = desafiosFacade.cancelarDesafio(id, usuarioId);
        if (cancelado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizarDesafio(@PathVariable UUID id) {
        boolean finalizado = desafiosFacade.concluirDesafio(id);
        if (finalizado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

