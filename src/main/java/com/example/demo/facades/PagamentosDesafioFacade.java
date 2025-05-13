package com.example.demo.facades;

import com.example.demo.applications.PagamentosDesafioApplication;
import com.example.demo.entities.PagamentoDesafio;
import com.example.demo.entities.Desafio;
import com.example.demo.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public PagamentoDesafio buscarPorId(int id) {
        return pagamentosDesafioApplication.buscarPorId(id);
    }

    public List<PagamentoDesafio> listarTodos() {
        return pagamentosDesafioApplication.listarTodos();
    }

    public void deletar(int id) {
        pagamentosDesafioApplication.deletar(id);
    }

    public boolean existePorId(int id) {
        return pagamentosDesafioApplication.existePorId(id);
    }

    public List<PagamentoDesafio> listarPorUsuario(int idUsuario) {
        return pagamentosDesafioApplication.listarPorUsuario(idUsuario);
    }

    public List<PagamentoDesafio> listarPorDesafio(int idDesafio) {
        return pagamentosDesafioApplication.listarPorDesafio(idDesafio);
    }
}
