package com.example.demo.controllers;

import com.example.demo.entities.MembrosGrupo;
import com.example.demo.enums.Status;
import com.example.demo.enums.TipoUsuario;
import com.example.demo.facades.MembrosGrupoFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/membros-grupo")
public class MembrosGrupoController {

    private final MembrosGrupoFacade membrosGrupoFacade;

    @Autowired
    public MembrosGrupoController(MembrosGrupoFacade membrosGrupoFacade) {
        this.membrosGrupoFacade = membrosGrupoFacade;
    }

    @PostMapping
    public ResponseEntity<MembrosGrupo> salvar(@Valid @RequestBody MembrosGrupo membrosGrupo) {
        MembrosGrupo salvo = membrosGrupoFacade.salvar(membrosGrupo);
        return ResponseEntity
                .created(URI.create("/membros-grupo/" + salvo.getId()))
                .body(salvo);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MembrosGrupo> buscarPorId(@PathVariable UUID id) {
        MembrosGrupo membro = membrosGrupoFacade.buscarPorUUID(id);
        return membro != null ? ResponseEntity.ok(membro) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<MembrosGrupo>> listarTodos() {
        List<MembrosGrupo> membros = membrosGrupoFacade.listarTodos();
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembrosGrupo> atualizar(@PathVariable UUID id, @Valid @RequestBody MembrosGrupo membrosGrupo) {
        MembrosGrupo existente = membrosGrupoFacade.buscarPorUUID(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        membrosGrupo.setId(id);
        MembrosGrupo atualizado = membrosGrupoFacade.salvar(membrosGrupo);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (membrosGrupoFacade.existePorUUID(id)) {
            membrosGrupoFacade.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<MembrosGrupo>> buscarPorGrupo(@PathVariable UUID grupoId) {
        List<MembrosGrupo> membros = membrosGrupoFacade.buscarPorGrupo(grupoId);
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MembrosGrupo>> buscarPorUsuario(@PathVariable UUID usuarioId) {
        List<MembrosGrupo> membros = membrosGrupoFacade.buscarPorUsuario(usuarioId);
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MembrosGrupo>> buscarPorStatus(@PathVariable Status status) {
        List<MembrosGrupo> membros = membrosGrupoFacade.buscarPorStatus(status);
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }

    @GetMapping("/tipo-usuario/{role}")
    public ResponseEntity<List<MembrosGrupo>> buscarPorRole(@PathVariable TipoUsuario role) {
        List<MembrosGrupo> membros = membrosGrupoFacade.buscarPorRole(role);
        return membros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(membros);
    }
}
