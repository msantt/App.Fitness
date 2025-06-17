
package com.example.demo.controllers;

import com.example.demo.applications.ConviteApplication;
import com.example.demo.entities.Convite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/convites")
public class ConviteController {
    @Autowired
    private ConviteApplication conviteApplication;

    @PostMapping("/enviar")
    public ResponseEntity<Convite> enviarConvite(
            @RequestParam UUID remetenteId,
            @RequestParam UUID convidadoId,
            @RequestParam UUID grupoOuDesafioId,
            @RequestParam boolean isGrupo) {
        Convite convite = conviteApplication.enviarConvite(remetenteId, convidadoId, grupoOuDesafioId, isGrupo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(convite.getId())
                .toUri();
        return ResponseEntity.created(location).body(convite);
    }

    @PostMapping("/{id}/responder")
    public ResponseEntity<Void> responderConvite(
            @PathVariable UUID id,
            @RequestParam boolean aceitar) {
        conviteApplication.responderConvite(id, aceitar);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/listar")
    public ResponseEntity<Iterable<Convite>> listarConvites() {
        Iterable<Convite> convites = conviteApplication.listarConvites();
        if (convites.iterator().hasNext()) {
            return ResponseEntity.ok(convites);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}