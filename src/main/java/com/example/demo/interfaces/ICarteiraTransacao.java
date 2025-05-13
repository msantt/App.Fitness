package com.example.demo.interfaces;

import com.example.demo.entities.CarteiraTransacao;

import java.util.List;

public interface ICarteiraTransacao {

    CarteiraTransacao salvar(CarteiraTransacao transacao);

    CarteiraTransacao buscarPorId(int id);

    List<CarteiraTransacao> listarTodas();

    void deletar(int id);

    List<CarteiraTransacao> listarPorCarteiraId(int idCarteira);
}
