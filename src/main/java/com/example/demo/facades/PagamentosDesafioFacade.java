package com.example.demo.facades;

import com.example.demo.applications.PagamentosDesafioApplication;
import com.example.demo.entities.PagamentoDesafio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PagamentosDesafioFacade {

    private final PagamentosDesafioApplication pagamentosDesafioApplication;

    @Autowired
    public PagamentosDesafioFacade(PagamentosDesafioApplication pagamentosDesafioApplication) {
        this.pagamentosDesafioApplication = pagamentosDesafioApplication;
    }

    public PagamentoDesafio salvar(PagamentoDesafio pagamentoDesafio) {
        return pagamentosDesafioApplication.salvar(pagamentoDesafio);
    }

    public PagamentoDesafio buscarPorId(UUID id) {
        return pagamentosDesafioApplication.buscarPorUuid(id);
    }

    public List<PagamentoDesafio> listarTodos() {
        return pagamentosDesafioApplication.listarTodos();
    }

    public void deletar(UUID id) {
        pagamentosDesafioApplication.deletar(id);
    }

    public boolean existePorUuid(UUID id) {
        return pagamentosDesafioApplication.existePorUuid(id);
    }

    public List<PagamentoDesafio> listarPorUsuario(UUID idUsuario) {
        return pagamentosDesafioApplication.listarPorUsuario(idUsuario);
    }

    public List<PagamentoDesafio> listarPorDesafio(UUID idDesafio) {
        return pagamentosDesafioApplication.listarPorDesafio(idDesafio);
    }
}
