package com.example.demo.controllers;

import com.example.demo.entities.PagamentoDesafio;
import com.example.demo.facades.PagamentosDesafioFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos-desafio")
public class PagamentosDesafioController {

    private final PagamentosDesafioFacade pagamentosDesafioFacade;

    @Autowired
    public PagamentosDesafioController(PagamentosDesafioFacade pagamentosDesafioFacade) {
        this.pagamentosDesafioFacade = pagamentosDesafioFacade;
    }

    @GetMapping("/")
    public ResponseEntity<List<PagamentoDesafio>> buscarTodos() {
        List<PagamentoDesafio> pagamentos = pagamentosDesafioFacade.listarTodos();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDesafio> buscarPorId(@PathVariable int id) {
        PagamentoDesafio pagamento = pagamentosDesafioFacade.buscarPorId(id);
        if (pagamento != null) {
            return ResponseEntity.ok(pagamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PagamentoDesafio>> listarPorUsuario(@PathVariable int idUsuario) {
        List<PagamentoDesafio> pagamentos = pagamentosDesafioFacade.listarPorUsuario(idUsuario);
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/desafio/{idDesafio}")
    public ResponseEntity<List<PagamentoDesafio>> listarPorDesafio(@PathVariable int idDesafio) {
        List<PagamentoDesafio> pagamentos = pagamentosDesafioFacade.listarPorDesafio(idDesafio);
        return ResponseEntity.ok(pagamentos);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody PagamentoDesafio pagamento) {
        pagamentosDesafioFacade.salvar(pagamento);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDesafio> atualizar(@PathVariable int id, @Valid @RequestBody PagamentoDesafio pagamento) {
        PagamentoDesafio existente = pagamentosDesafioFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        pagamento.setIdPagamento(id);
        PagamentoDesafio atualizado = pagamentosDesafioFacade.salvar(pagamento);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        pagamentosDesafioFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
