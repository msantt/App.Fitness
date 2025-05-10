package com.example.demo.controllers;

import com.example.demo.entities.CheckIn;
import com.example.demo.facades.CategoriasFacade;
import com.example.demo.facades.CheckinFacade;
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


    CheckinFacade checkinFacade;

    @Autowired
    public CheckinController(CheckinFacade checkinFacade, CategoriasFacade categoriasFacade) {
        this.checkinFacade = checkinFacade;
    }

    @GetMapping("/")
    public ResponseEntity<List<CheckIn>> buscarTodos() {
        List<CheckIn> checkIns = checkinFacade.listarTodos();
        return ResponseEntity.ok(checkIns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckIn> buscarPorId(@PathVariable int id) {
        CheckIn checkIn = checkinFacade.buscarPorId(id);
        if (checkIn != null) {
            return ResponseEntity.ok(checkIn);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<CheckIn>> buscarPorUsuario(@PathVariable int id) {
        List<CheckIn> checkIns = checkinFacade.buscarPorIdUsuario(id);
        return checkIns.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(checkIns);
    }


    @PostMapping
    public ResponseEntity<Void> salvar(@Valid  @RequestBody CheckIn checkIns) {
        checkinFacade.salvar(checkIns);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckIn> atualizar(@PathVariable int id,@Valid @RequestBody CheckIn checkIns) {
        CheckIn existente = checkinFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        checkIns.setId(id);
        CheckIn atualizado = checkinFacade.salvar(checkIns);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        checkinFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
