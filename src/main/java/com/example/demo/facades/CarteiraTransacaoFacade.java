package com.example.demo.facades;

import com.example.demo.applications.CarteiraTransacoesApplication;
import com.example.demo.entities.CarteiraTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarteiraTransacaoFacade {

    private final CarteiraTransacoesApplication carteiraTransacoesApplication;

    @Autowired
    public CarteiraTransacaoFacade(CarteiraTransacoesApplication carteiraTransacoesApplication) {
        this.carteiraTransacoesApplication = carteiraTransacoesApplication;
    }

    public CarteiraTransacao salvar(CarteiraTransacao transacao) {
        return carteiraTransacoesApplication.salvar(transacao);
    }

    public CarteiraTransacao buscarPorId(int id) {
        return carteiraTransacoesApplication.buscarPorId(id);
    }

    public List<CarteiraTransacao> listarTodas() {
        return carteiraTransacoesApplication.listarTodas();
    }

    public void deletar(int id) {
        carteiraTransacoesApplication.deletar(id);
    }

    public List<CarteiraTransacao> listarPorCarteiraId(int idCarteira) {
        return carteiraTransacoesApplication.listarPorCarteiraId(idCarteira);
    }
}
