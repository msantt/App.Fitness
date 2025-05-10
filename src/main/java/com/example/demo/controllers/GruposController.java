package com.example.demo.controllers;

import com.example.demo.entities.Grupo;
import com.example.demo.facades.GruposFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grupos")
public class GruposController {

    private final GruposFacade gruposFacade;

    @Autowired
    public GruposController(GruposFacade gruposFacade) {
        this.gruposFacade = gruposFacade;
    }

    @GetMapping("/")
    public ResponseEntity<List<Grupo>> buscarTodos() {
        List<Grupo> grupos = gruposFacade.listarTodos();
        return ResponseEntity.ok(grupos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> buscarPorId(@PathVariable int id) {
        Grupo grupo = gruposFacade.buscarPorId(id);
        if (grupo != null) {
            return ResponseEntity.ok(grupo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Grupo>> buscarPorNome(@PathVariable String nome) {
        List<Grupo> grupos = gruposFacade.buscarPorNome(nome);
        return grupos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(grupos);
    }

    @GetMapping("/criador/{id}")
    public ResponseEntity<List<Grupo>> buscarPorCriador(@PathVariable int id) {
        List<Grupo> grupos = gruposFacade.buscarPorCriadorId(id);
        return grupos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(grupos);
    }

    @PostMapping
    public ResponseEntity<Grupo> salvar(@Valid @RequestBody Grupo grupo) {
        Grupo salvo = gruposFacade.salvar(grupo);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> atualizar(@PathVariable int id, @Valid @RequestBody Grupo grupo) {
       Grupo existente = gruposFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        grupo.setId(id);
        Grupo atualizado = gruposFacade.salvar(grupo);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        gruposFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }
}