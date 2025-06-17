package com.example.demo.controllers;

import com.example.demo.entities.PagamentoDesafio;
import com.example.demo.facades.PagamentosDesafioFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos-desafios")
public class PagamentosDesafioController {

    private final PagamentosDesafioFacade pagamentosDesafioFacade;

    @Autowired
    public PagamentosDesafioController(PagamentosDesafioFacade pagamentosDesafioFacade) {
        this.pagamentosDesafioFacade = pagamentosDesafioFacade;
    }

    @GetMapping
    public ResponseEntity<List<PagamentoDesafio>> buscarTodos() {
        List<PagamentoDesafio> pagamentos = pagamentosDesafioFacade.listarTodos();
        if (pagamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDesafio> buscarPorId(@PathVariable UUID id) {
        PagamentoDesafio pagamento = pagamentosDesafioFacade.buscarPorId(id);
        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pagamento);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PagamentoDesafio>> listarPorUsuario(@PathVariable UUID idUsuario) {
        List<PagamentoDesafio> pagamentos = pagamentosDesafioFacade.listarPorUsuario(idUsuario);
        if (pagamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/desafio/{idDesafio}")
    public ResponseEntity<List<PagamentoDesafio>> listarPorDesafio(@PathVariable UUID idDesafio) {
        List<PagamentoDesafio> pagamentos = pagamentosDesafioFacade.listarPorDesafio(idDesafio);
        if (pagamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagamentos);
    }

    @PostMapping
    public ResponseEntity<PagamentoDesafio> salvar(@Valid @RequestBody PagamentoDesafio pagamento) {
        PagamentoDesafio salvo = pagamentosDesafioFacade.salvar(pagamento);
        return ResponseEntity
                .created(URI.create("/pagamentos-desafios/" + salvo.getIdPagamento()))
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDesafio> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody PagamentoDesafio pagamento) {

        PagamentoDesafio existente = pagamentosDesafioFacade.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        pagamento.setIdPagamento(id);
        PagamentoDesafio atualizado = pagamentosDesafioFacade.salvar(pagamento);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        pagamentosDesafioFacade.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
