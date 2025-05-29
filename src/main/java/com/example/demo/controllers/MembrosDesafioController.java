package com.example.demo.controllers;

import com.example.demo.entities.MembrosDesafio;
import com.example.demo.entities.MembrosGrupo;
import com.example.demo.facades.MembrosDesafioFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membros-desafio")
public class MembrosDesafioController {

    private final MembrosDesafioFacade facade;

    public MembrosDesafioController(MembrosDesafioFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public ResponseEntity<MembrosDesafio> criar(@RequestBody MembrosDesafio membro) {
        return ResponseEntity.ok(facade.criar(membro));
    }

    @GetMapping("/")
    public ResponseEntity<List<MembrosDesafio>> listar() {
        return ResponseEntity.ok(facade.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembrosDesafio> buscarPorId(@PathVariable int id) {
        MembrosDesafio membro = facade.buscar(id);
        if (membro != null) {
            return ResponseEntity.ok(membro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/desafio/{desafioId}")
    public ResponseEntity<List<MembrosDesafio>> buscarPorDesafio(@PathVariable int desafioId) {
        List<MembrosDesafio> membros = facade.buscarPorDesafio(desafioId);
        if (membros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(membros);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        facade.remover(id);
        return ResponseEntity.noContent().build();
    }
}
