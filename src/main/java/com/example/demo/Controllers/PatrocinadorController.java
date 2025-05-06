package com.example.demo.Controllers;

import com.example.demo.Entities.Patrocinador;
import com.example.demo.Facades.PatrocinadorFacede;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patrocinador")
public class PatrocinadorController {

    private final PatrocinadorFacede patrocinadorFacede;

    @Autowired
    public PatrocinadorController(PatrocinadorFacede patrocinadorFacede) {
        this.patrocinadorFacede = patrocinadorFacede;
    }

    @GetMapping
    public ResponseEntity<List<Patrocinador>> buscarTodos() {
        List<Patrocinador> patrocinadores = patrocinadorFacede.listarPatrocinadores();
        return ResponseEntity.ok(patrocinadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patrocinador> buscarPorId(@PathVariable int id) {
        return patrocinadorFacede.buscarPatrocinadorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Patrocinador> buscarPorNome(@PathVariable String nome) {
        return patrocinadorFacede.buscarPatrocinadorPorNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patrocinador> salvar(@Valid @RequestBody Patrocinador patrocinador) {
        Patrocinador salvo = patrocinadorFacede.salvarPatrocinador(patrocinador);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patrocinador> atualizar(@PathVariable int id, @Valid @RequestBody Patrocinador patrocinador) {
        Optional<Patrocinador> existenteOpt = patrocinadorFacede.buscarPatrocinadorPorId(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        patrocinador.setId(id);
        Patrocinador atualizado = patrocinadorFacede.salvarPatrocinador(patrocinador);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        patrocinadorFacede.deletarPatrocinador(id);
        return ResponseEntity.noContent().build();
    }
}
