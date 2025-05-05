package com.example.demo.Controllers;

import com.example.demo.Entities.Grupos;
import com.example.demo.Facades.GruposFacede;
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

    private final GruposFacede gruposFacede;

    @Autowired
    public GruposController(GruposFacede gruposFacede) {
        this.gruposFacede = gruposFacede;
    }

    @GetMapping("/")
    public ResponseEntity<List<Grupos>> buscarTodos() {
        List<Grupos> grupos = gruposFacede.listarTodos();
        return ResponseEntity.ok(grupos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupos> buscarPorId(@PathVariable int id) {
        Optional<Grupos> grupo = gruposFacede.buscarPorId(id);
        return grupo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Grupos>> buscarPorNome(@PathVariable String nome) {
        List<Grupos> grupos = gruposFacede.buscarPorNome(nome);
        return grupos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(grupos);
    }

    @GetMapping("/criador/{id}")
    public ResponseEntity<List<Grupos>> buscarPorCriador(@PathVariable int id) {
        List<Grupos> grupos = gruposFacede.buscarPorCriadorId(id);
        return grupos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(grupos);
    }

    @PostMapping
    public ResponseEntity<Grupos> salvar(@Valid @RequestBody Grupos grupos) {
        Grupos salvo = gruposFacede.salvar(grupos);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupos> atualizar(@PathVariable int id, @Valid @RequestBody Grupos grupos) {
        Optional<Grupos> existente = gruposFacede.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        grupos.setId(id);
        Grupos atualizado = gruposFacede.salvar(grupos);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        gruposFacede.deletar(id);
        return ResponseEntity.noContent().build();
    }
}