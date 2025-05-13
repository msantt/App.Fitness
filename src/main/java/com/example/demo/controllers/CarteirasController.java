package com.example.demo.controllers;

import com.example.demo.entities.Carteira;
import com.example.demo.facades.CarteirasFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carteiras")
public class CarteirasController {

    private final CarteirasFacade carteirasFacade;

    @Autowired
    public CarteirasController(CarteirasFacade carteirasFacade) {
        this.carteirasFacade = carteirasFacade;
    }

    @GetMapping("/")
    public ResponseEntity<List<Carteira>> buscarTodos() {
        List<Carteira> carteiras = carteirasFacade.listarTodos();
        return ResponseEntity.ok(carteiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carteira> buscarPorId(@PathVariable int id) {
        Carteira carteira = carteirasFacade.buscarPorId(id);
        if (carteira != null) {
            return ResponseEntity.ok(carteira);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Carteira> buscarPorIdUsuario(@PathVariable int idUsuario) {
        Carteira carteira = carteirasFacade.buscarPorUsuarioId(idUsuario);
        if (carteira != null) {
            return ResponseEntity.ok(carteira);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody Carteira carteira) {
        carteirasFacade.salvar(carteira);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carteira> atualizar(@PathVariable int id, @Valid @RequestBody Carteira carteira) {
        Carteira existente = carteirasFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        carteira.setIdCarteira(id);
        Carteira atualizado = carteirasFacade.salvar(carteira);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        carteirasFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
