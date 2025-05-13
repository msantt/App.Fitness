package com.example.demo.applications;

import com.example.demo.entities.CarteiraTransacao;
import com.example.demo.interfaces.ICarteiraTransacao;
import com.example.demo.repositories.CarteiraTransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteiraTransacoesApplication implements ICarteiraTransacao {

    private final CarteiraTransacaoRepository carteiraTransacaoRepository;

    @Autowired
    public CarteiraTransacoesApplication(CarteiraTransacaoRepository carteiraTransacaoRepository) {
        this.carteiraTransacaoRepository = carteiraTransacaoRepository;
    }

    @Override
    public CarteiraTransacao salvar(CarteiraTransacao transacao) {
        return carteiraTransacaoRepository.save(transacao);
    }

    @Override
    public CarteiraTransacao buscarPorId(int id) {
        return carteiraTransacaoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<CarteiraTransacao> listarTodas() {
        return carteiraTransacaoRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        carteiraTransacaoRepository.deleteById(id);
    }

    @Override
    public List<CarteiraTransacao> listarPorCarteiraId(int idCarteira) {
        return carteiraTransacaoRepository.findByIdCarteira(idCarteira);
    }

    @Override
    public List<CarteiraTransacao> listarPorTipo(int tipoTransacao) {
        return carteiraTransacaoRepository.findByIdCarteira(tipoTransacao);
    }
}
