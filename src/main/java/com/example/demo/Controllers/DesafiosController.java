package com.example.demo.Controllers;

import com.example.demo.Entities.Desafios;
import com.example.demo.Entities.Usuarios;
import com.example.demo.Facades.DesafiosFacede;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/desafios")
public class DesafiosController {
    DesafiosFacede desafiosFacede;

    @Autowired
    public DesafiosController(DesafiosFacede desafiosFacede) {
        this.desafiosFacede = desafiosFacede;
    }

    @GetMapping("/")
    public ResponseEntity<List<Desafios>> buscarTodos() {
        List<Desafios> desafios = desafiosFacede.listarTodos();
        return ResponseEntity.ok(desafios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Desafios> buscarPorId(@PathVariable int id) {
        return desafiosFacede.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Desafios>> buscarPorCategoria(@PathVariable int id) {
        List<Desafios> desafios = desafiosFacede.buscarPorIdCategoria(id);
        return desafios.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(desafios);
    }

    @GetMapping("/grupo/{id}")// erro
    public ResponseEntity<List<Desafios>> buscarPorGrupo(@PathVariable int id) {
        List<Desafios> desafios = desafiosFacede.buscarPorIdGrupo(id);
        return desafios.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(desafios);
    }


    @PostMapping
    public ResponseEntity<Void> salvar( @Valid @RequestBody Desafios desafios) {
        desafiosFacede.salvar(desafios);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Desafios> atualizar(@PathVariable int id,@Valid @RequestBody Desafios desafios) {
        Optional<Desafios> existente = desafiosFacede.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        desafios.setId(id);
        Desafios atualizado = desafiosFacede.salvar(desafios);
        return ResponseEntity.ok(atualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        desafiosFacede.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
