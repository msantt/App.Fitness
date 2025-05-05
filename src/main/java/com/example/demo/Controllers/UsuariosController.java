package com.example.demo.Controllers;

import com.example.demo.Entities.Usuarios;
import com.example.demo.Facades.UsuariosFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosFacade usuariosFacade;

    @Autowired
    public UsuariosController(UsuariosFacade usuariosFacade) {
        this.usuariosFacade = usuariosFacade;
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuarios>> buscarTodos() {
        List<Usuarios> usuarios = usuariosFacade.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> buscarPorId(@PathVariable int id) {
        return usuariosFacade.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<Usuarios> buscarPorEmail(@PathVariable String email) {
        return usuariosFacade.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody Usuarios usuarios) {
        usuariosFacade.salvar(usuarios);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> atualizar(@PathVariable int id,@Valid @RequestBody Usuarios usuarios) {
        Optional<Usuarios> existente = usuariosFacade.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuarios.setId(id);
        Usuarios atualizado = usuariosFacade.salvar(usuarios);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        usuariosFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
