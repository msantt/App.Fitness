package com.example.demo.controllers;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Desafio;
import com.example.demo.facades.CategoriasFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    private final CategoriasFacade categoriasFacade;

    @Autowired
    public CategoriasController( CategoriasFacade categoriasFacade) {
        this.categoriasFacade = categoriasFacade;
    }

    @GetMapping("/")
    public ResponseEntity<List<Categoria>> buscarTodos() {
        List<Categoria> categorias = categoriasFacade.listarTodos();
        return ResponseEntity.ok(categorias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable UUID id) {
        Categoria categoria = categoriasFacade.buscarPorId(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Categoria> buscarPorNome(@PathVariable String nome) {
        Categoria categoria = categoriasFacade.buscarPorNome(nome);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody Categoria categorias) {
        categoriasFacade.salvar(categorias);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable UUID id,@Valid @RequestBody Categoria categorias) {
        Categoria existente = categoriasFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        categorias.setId(id);
        Categoria atualizado = categoriasFacade.salvar(categorias);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        categoriasFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/com-desafios-ativos")
    public ResponseEntity<List<Categoria>> listarCategoriasComDesafiosAtivos() {
        List<Categoria> categorias = categoriasFacade.listarCategoriasComDesafiosAtivos();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{idCategoria}/desafios")
    public ResponseEntity<List<Desafio>> listarDesafiosPorCategoriaId(@PathVariable UUID idCategoria) {
        List<Desafio> desafios = categoriasFacade.listarDesafiosPorCategoriaUUID(idCategoria);
        return ResponseEntity.ok(desafios);
    }

    @GetMapping("/{idCategoria}/desafios-ativos")
    public ResponseEntity<List<Desafio>> listarDesafiosAtivosPorCategoria(@PathVariable UUID idCategoria) {
        List<Desafio> desafios = categoriasFacade.listarDesafiosAtivosPorCategoria(idCategoria);
        return ResponseEntity.ok(desafios);
    }

}
