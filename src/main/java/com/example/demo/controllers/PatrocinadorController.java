package com.example.demo.controllers;

import com.example.demo.entities.Patrocinador;
import com.example.demo.facades.PatrocinadorFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patrocinadores")
public class PatrocinadorController {

    private final PatrocinadorFacade patrocinadorFacade;

    @Autowired
    public PatrocinadorController(PatrocinadorFacade patrocinadorFacade) {
        this.patrocinadorFacade = patrocinadorFacade;
    }

    @GetMapping
    public ResponseEntity<List<Patrocinador>> buscarTodos() {
        List<Patrocinador> patrocinadores = patrocinadorFacade.listarPatrocinadores();
        if (patrocinadores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(patrocinadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrocinador> buscarPorId(@PathVariable UUID id) {
        Patrocinador patrocinador = patrocinadorFacade.buscarPatrocinadorPorId(id);
        if (patrocinador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patrocinador);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Patrocinador> buscarPorNome(@PathVariable String nome) {
        Patrocinador patrocinador = patrocinadorFacade.buscarPatrocinadorPorNome(nome);
        if (patrocinador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patrocinador);
    }

    @PostMapping
    public ResponseEntity<Patrocinador> salvar(@Valid @RequestBody Patrocinador patrocinador) {
        Patrocinador salvo = patrocinadorFacade.salvarPatrocinador(patrocinador);
        return ResponseEntity
                .created(URI.create("/patrocinadores/" + salvo.getId()))
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patrocinador> atualizar(@PathVariable UUID id, @Valid @RequestBody Patrocinador patrocinador) {
        Patrocinador existente = patrocinadorFacade.buscarPatrocinadorPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        patrocinador.setId(id);
        Patrocinador atualizado = patrocinadorFacade.salvarPatrocinador(patrocinador);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        patrocinadorFacade.deletarPatrocinador(id);
        return ResponseEntity.noContent().build();
    }
}
