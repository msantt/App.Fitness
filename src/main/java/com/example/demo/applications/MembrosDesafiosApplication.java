package com.example.demo.applications;

import com.example.demo.entities.MembrosDesafio;
import com.example.demo.interfaces.IMembrosDesafio;
import com.example.demo.repositories.MembrosDesafioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembrosDesafiosApplication implements IMembrosDesafio {

    private final MembrosDesafioRepository repository;

    public MembrosDesafiosApplication(MembrosDesafioRepository repository) {
        this.repository = repository;
    }

    @Override
    public MembrosDesafio salvar(MembrosDesafio membroDesafio) {
        return repository.save(membroDesafio);
    }

    @Override
    public List<MembrosDesafio> listarTodos() {
        return repository.findAll();
    }

    @Override
    public MembrosDesafio buscarPorId(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void deletar(int id) {
        repository.deleteById(id);
    }
}
