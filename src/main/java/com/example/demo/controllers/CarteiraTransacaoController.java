package com.example.demo.controllers;

import com.example.demo.entities.CarteiraTransacao;
import com.example.demo.facades.CarteiraTransacaoFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class CarteiraTransacaoController {

    private final CarteiraTransacaoFacade transacaoFacade;

    @Autowired
    public CarteiraTransacaoController(CarteiraTransacaoFacade transacaoFacade) {
        this.transacaoFacade = transacaoFacade;
    }

    @GetMapping("/")
    public ResponseEntity<List<CarteiraTransacao>> buscarTodas() {
        List<CarteiraTransacao> transacoes = transacaoFacade.listarTodas();
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteiraTransacao> buscarPorId(@PathVariable int id) {
        CarteiraTransacao transacao = transacaoFacade.buscarPorId(id);
        if (transacao != null) {
            return ResponseEntity.ok(transacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/carteira/{idCarteira}")
    public ResponseEntity<List<CarteiraTransacao>> buscarPorCarteira(@PathVariable int idCarteira) {
        List<CarteiraTransacao> transacoes = transacaoFacade.listarPorCarteiraId(idCarteira);
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/tipo/{tipoTransacao}")
    public ResponseEntity<List<CarteiraTransacao>> buscarPorTipo(@PathVariable int tipoTransacao) {
        List<CarteiraTransacao> transacoes = transacaoFacade.listarPorCarteiraId(tipoTransacao);
        return ResponseEntity.ok(transacoes);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody CarteiraTransacao transacao) {
        transacaoFacade.salvar(transacao);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarteiraTransacao> atualizar(@PathVariable int id, @Valid @RequestBody CarteiraTransacao transacao) {
        CarteiraTransacao existente = transacaoFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        transacao.setIdTransacao(id);
        CarteiraTransacao atualizado = transacaoFacade.salvar(transacao);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        transacaoFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
