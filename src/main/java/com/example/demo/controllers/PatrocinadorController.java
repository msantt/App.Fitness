package com.example.demo.controllers;

import com.example.demo.entities.Patrocinador;
import com.example.demo.facades.PatrocinadorFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patrocinador")
public class PatrocinadorController {

    private final PatrocinadorFacade patrocinadorFacade;

    @Autowired
    public PatrocinadorController(PatrocinadorFacade patrocinadorFacade) {
        this.patrocinadorFacade = patrocinadorFacade;
    }

    @GetMapping("/")
    public ResponseEntity<List<Patrocinador>> buscarTodos() {
        List<Patrocinador> patrocinadores = patrocinadorFacade.listarPatrocinadores();
        return ResponseEntity.ok(patrocinadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrocinador> buscarPorId(@PathVariable int id) {
        Patrocinador patrocinador = patrocinadorFacade.buscarPatrocinadorPorId(id);
        if (patrocinador != null) {
            return ResponseEntity.ok(patrocinador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<Patrocinador> buscarPorNome(@PathVariable String nome) {
        Patrocinador patrocinador = patrocinadorFacade.buscarPatrocinadorPorNome(nome);
        if (patrocinador != null) {
            return ResponseEntity.ok(patrocinador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Patrocinador> salvar(@Valid @RequestBody Patrocinador patrocinador) {
        Patrocinador salvo = patrocinadorFacade.salvarPatrocinador(patrocinador);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patrocinador> atualizar(@PathVariable int id, @Valid @RequestBody Patrocinador patrocinador) {
        Patrocinador existenteOpt = patrocinadorFacade.buscarPatrocinadorPorId(id);
        if (existenteOpt == null) {
            return ResponseEntity.notFound().build();
        }
        patrocinador.setId(id);
        Patrocinador atualizado = patrocinadorFacade.salvarPatrocinador(patrocinador);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        patrocinadorFacade.deletarPatrocinador(id);
        return ResponseEntity.noContent().build();
    }
}
