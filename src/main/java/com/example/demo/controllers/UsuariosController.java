package com.example.demo.controllers;

import com.example.demo.entities.Desafio;
import com.example.demo.entities.Notificacao;
import com.example.demo.entities.Usuario;
import com.example.demo.facades.UsuariosFacade;
import com.example.demo.repositories.NotificacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosFacade usuariosFacade;
    private final NotificacaoRepository notificacaoRepository;

    @Autowired
    public UsuariosController(UsuariosFacade usuariosFacade, NotificacaoRepository notificacaoRepository) {
        this.usuariosFacade = usuariosFacade;
        this.notificacaoRepository = notificacaoRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> usuarios = usuariosFacade.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable UUID id) {
        Usuario usuario = usuariosFacade.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        Usuario usuario = usuariosFacade.buscarPorEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{usuarioId}/recomendacoes/populares")
    public ResponseEntity<List<Desafio>> recomendarPopulares(@PathVariable UUID usuarioId) {
        List<Desafio> recomendacoes = usuariosFacade.recomendarDesafiosPopulares(usuarioId);
        if (recomendacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recomendacoes);
    }

    @GetMapping("/{uuid}/notificacoes")
    public List<Notificacao> listarNotificacoes(@PathVariable UUID uuid) {
        return notificacaoRepository.findByUsuarioUuidOrderByDataCriacaoDesc(uuid);
    }



    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody Usuario usuario) {
        usuariosFacade.salvar(usuario);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable UUID id, @Valid @RequestBody Usuario usuario) {
        Usuario existente = usuariosFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        Usuario atualizado = usuariosFacade.salvar(usuario);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        usuariosFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
