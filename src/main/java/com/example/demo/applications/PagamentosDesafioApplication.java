package com.example.demo.applications;

import com.example.demo.entities.PagamentoDesafio;
import com.example.demo.interfaces.IPagamentosDesafio;
import com.example.demo.repositories.PagamentoDesafioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public PagamentoDesafio buscarPorUuid(UUID id) {
        return pagamentoDesafioRepository.findByUuid(id);
    }

    @Override
    public List<PagamentoDesafio> listarTodos() {
        return pagamentoDesafioRepository.findAll();
    }

    @Override
    public void deletar(UUID id) {
        pagamentoDesafioRepository.deleteByUuid(id);
    }

    @Override
    public boolean existePorUuid(UUID id) {
        return pagamentoDesafioRepository.existsByUuid(id);
    }

    @Override
    public List<PagamentoDesafio> listarPorUsuario(UUID idUsuario) {
        return pagamentoDesafioRepository.findByUsuarioUuid(idUsuario);
    }

    @Override
    public List<PagamentoDesafio> listarPorDesafio(UUID idDesafio) {
        return pagamentoDesafioRepository.findByDesafioUuid(idDesafio);
    }
}
