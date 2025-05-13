package com.example.demo.controllers;

import com.example.demo.entities.Recompensa;
import com.example.demo.facades.RecompensaFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recompensa")
public class RecompensaController {

    private final RecompensaFacade recompensaFacade;

    @Autowired
    public RecompensaController(RecompensaFacade recompensaFacade) {
        this.recompensaFacade = recompensaFacade;
    }

    @GetMapping
    public ResponseEntity<List<Recompensa>> buscarTodas() {
        List<Recompensa> recompensas = recompensaFacade.listarRecompensas();
        return ResponseEntity.ok(recompensas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recompensa> buscarPorId(@PathVariable int id) {
        Recompensa recompensa = recompensaFacade.buscarRecompensaPorId(id);
        if (recompensa != null) {
            return ResponseEntity.ok(recompensa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Recompensa> salvar(@Valid @RequestBody Recompensa recompensa) {
        Recompensa salva = recompensaFacade.salvarRecompensa(recompensa);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recompensa> atualizar(@PathVariable int id, @Valid @RequestBody Recompensa recompensa) {
        Recompensa existente = recompensaFacade.buscarRecompensaPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        recompensa.setId(id);
        Recompensa atualizada = recompensaFacade.salvarRecompensa(recompensa);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        recompensaFacade.deletarRecompensa(id);
        return ResponseEntity.noContent().build();
    }
}
