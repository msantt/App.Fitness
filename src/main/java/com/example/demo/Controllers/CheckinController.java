package com.example.demo.Controllers;

import com.example.demo.Entities.Categoria;
import com.example.demo.Entities.CheckIn;
import com.example.demo.Entities.Usuarios;
import com.example.demo.Facades.CategoriasFacede;
import com.example.demo.Facades.CheckinFacede;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/check-in")
public class CheckinController {


    CheckinFacede checkinFacede;

    @Autowired
    public CheckinController(CheckinFacede checkinFacede, CategoriasFacede categoriasFacede) {
        this.checkinFacede = checkinFacede;
    }

    @GetMapping("/")
    public ResponseEntity<List<CheckIn>> buscarTodos() {
        List<CheckIn> checkIns = checkinFacede.listarTodos();
        return ResponseEntity.ok(checkIns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckIn> buscarPorId(@PathVariable int id) {
        return checkinFacede.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<CheckIn>> buscarPorUsuario(@PathVariable int id) {
        List<CheckIn> checkIns = checkinFacede.buscarPorIdUsuario(id);
        return checkIns.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(checkIns);
    }


    @PostMapping
    public ResponseEntity<Void> salvar(@Valid  @RequestBody CheckIn checkIns) {
        checkinFacede.salvar(checkIns);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckIn> atualizar(@PathVariable int id,@Valid @RequestBody CheckIn checkIns) {
        Optional<CheckIn> existente = checkinFacede.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        checkIns.setId(id);
        CheckIn atualizado = checkinFacede.salvar(checkIns);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        checkinFacede.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
