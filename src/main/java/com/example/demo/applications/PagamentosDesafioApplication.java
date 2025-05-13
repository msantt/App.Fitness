package com.example.demo.applications;

import com.example.demo.entities.PagamentoDesafio;
import com.example.demo.interfaces.IPagamentosDesafio;
import com.example.demo.repositories.PagamentoDesafioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentosDesafioApplication implements IPagamentosDesafio {

    private final PagamentoDesafioRepository pagamentoDesafioRepository;

    @Autowired
    public PagamentosDesafioApplication(PagamentoDesafioRepository pagamentoDesafioRepository) {
        this.pagamentoDesafioRepository = pagamentoDesafioRepository;
    }

    @Override
    public PagamentoDesafio salvar(PagamentoDesafio pagamentoDesafio) {
        return pagamentoDesafioRepository.save(pagamentoDesafio);
    }

    @Override
    public PagamentoDesafio buscarPorId(int id) {
        return pagamentoDesafioRepository.findById(id).orElseThrow();
    }

    @Override
    public List<PagamentoDesafio> listarTodos() {
        return pagamentoDesafioRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        pagamentoDesafioRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(int id) {
        return pagamentoDesafioRepository.existsById(id);
    }

    @Override
    public List<PagamentoDesafio> listarPorUsuario(int idUsuario) {
        return pagamentoDesafioRepository.findByUsuarioId(idUsuario);
    }

    @Override
    public List<PagamentoDesafio> listarPorDesafio(int idDesafio) {
        return pagamentoDesafioRepository.findByDesafioId(idDesafio);
    }
}
