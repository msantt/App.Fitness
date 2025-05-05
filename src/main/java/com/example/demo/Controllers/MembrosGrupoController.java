package com.example.demo.Controllers;


import com.example.demo.Entities.Desafios;
import com.example.demo.Entities.Grupos;
import com.example.demo.Entities.MembrosGrupo;
import com.example.demo.Facades.MembrosGrupoFacede;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/membros-grupo")
public class MembrosGrupoController {

    private final MembrosGrupoFacede membrosGrupoFacade;

    @Autowired
    public MembrosGrupoController(MembrosGrupoFacede membrosGrupoFacade) {
        this.membrosGrupoFacade = membrosGrupoFacade;
    }

    @PostMapping
    public ResponseEntity<MembrosGrupo> salvar( @Valid @RequestBody MembrosGrupo membrosGrupo) {
        MembrosGrupo salvo = membrosGrupoFacade.salvar(membrosGrupo);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembrosGrupo> buscarPorId(@PathVariable int id) {
        Optional<MembrosGrupo> membro = membrosGrupoFacade.buscarPorId(id);
        return membro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MembrosGrupo>> listarTodos() {
        List<MembrosGrupo> membros = membrosGrupoFacade.listarTodos();
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }

    @PutMapping
    public ResponseEntity<MembrosGrupo> atualizar(@PathVariable int id,@Valid @RequestBody MembrosGrupo membrosGrupo){
        Optional<MembrosGrupo> existente = membrosGrupoFacade.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        membrosGrupo.setId(id);
        MembrosGrupo atualizado = membrosGrupoFacade.salvar(membrosGrupo);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (membrosGrupoFacade.existePorId(id)) {
            membrosGrupoFacade.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<MembrosGrupo>> buscarPorGrupo(@PathVariable int grupoId) {
        List<MembrosGrupo> membros = membrosGrupoFacade.buscarPorGrupo(grupoId);
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MembrosGrupo>> buscarPorUsuario(@PathVariable int usuarioId) {
        List<MembrosGrupo> membros = membrosGrupoFacade.buscarPorUsuario(usuarioId);
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MembrosGrupo>> buscarPorStatus(@PathVariable boolean status) {
        List<MembrosGrupo> membros = membrosGrupoFacade.buscarPorStatus(status);
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }


    @GetMapping("/role/{role}")
    public ResponseEntity<List<MembrosGrupo>> buscarPorRole(@PathVariable String role) {
        List<MembrosGrupo> membros = membrosGrupoFacade.buscarPorRole(role);
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }
}
