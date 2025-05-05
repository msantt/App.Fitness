package com.example.demo.Controllers;

import com.example.demo.Entities.Categoria;
import com.example.demo.Facades.CategoriasFacede;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    private final CategoriasFacede categoriasFacede;

    @Autowired
    public CategoriasController( CategoriasFacede categoriasFacede) {
        this.categoriasFacede = categoriasFacede;
    }

    @GetMapping("/")
    public ResponseEntity<List<Categoria>> buscarTodos() {
        List<Categoria> categorias = categoriasFacede.listarTodos();
        return ResponseEntity.ok(categorias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable int id) {
        return categoriasFacede.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<Optional<Categoria>> buscarPorNome(@PathVariable String nome) {
        return categoriasFacede.buscarPorNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody Categoria categorias) {
        categoriasFacede.salvar(categorias);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable int id,@Valid @RequestBody Categoria categorias) {
        Optional<Categoria> existente = categoriasFacede.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categorias.setId(id);
        Categoria atualizado = categoriasFacede.salvar(categorias);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        categoriasFacede.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
